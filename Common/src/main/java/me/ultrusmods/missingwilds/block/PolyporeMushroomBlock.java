package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.MissingWildsModCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PolyporeMushroomBlock extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty AMOUNT = IntegerProperty.create("amount", 1, 2);

	private static final VoxelShape SOUTH_ONE = Block.box(2, 9.0, 0, 14.0, 12.0, 6.0);
	private static final VoxelShape SOUTH_TWO = Block.box(0, 7.0, 0, 16.0, 12.0, 7.0);
	private static final VoxelShape EAST_ONE = Block.box(0, 9.0, 2, 6.0, 12.0, 14.0);
	private static final VoxelShape EAST_TWO = Block.box(0, 7.0, 0, 7.0, 12.0, 16.0);
	private static final VoxelShape NORTH_ONE = Block.box(2, 9.0, 10, 14.0, 12.0, 16);
	private static final VoxelShape NORTH_TWO = Block.box(0, 7.0, 9, 16.0, 12.0, 16);
	private static final VoxelShape WEST_ONE = Block.box(10, 9.0, 2, 16, 12.0, 14.0);
	private static final VoxelShape WEST_TWO = Block.box(9, 7.0, 0, 16, 12.0, 16.0);

	public PolyporeMushroomBlock(Properties settings) {
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 1));

	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		switch(state.getValue(FACING)) {
			case SOUTH -> {
				return state.getValue(AMOUNT) == 1 ? SOUTH_ONE : SOUTH_TWO;
			}
			case NORTH -> {
				return state.getValue(AMOUNT) == 1 ? NORTH_ONE : NORTH_TWO;
			}
			case EAST -> {
				return state.getValue(AMOUNT) == 1 ? EAST_ONE : EAST_TWO;
			}
			case WEST -> {
				return state.getValue(AMOUNT) == 1 ? WEST_ONE : WEST_TWO;
			}
		}
		return super.getShape(state, world, pos, context);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
		if (random.nextInt(50) == 0 && state.getValue(AMOUNT) == 1) {
			world.setBlock(pos, state.setValue(AMOUNT, 2), Block.UPDATE_CLIENTS);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
		if (blockState.is(this)) {
			return blockState.cycle(AMOUNT);
		}

		for(Direction direction : ctx.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal()) {
				return defaultBlockState().setValue(FACING, direction.getOpposite());
			}
		}

		return super.getStateForPlacement(ctx);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return !context.isSecondaryUseActive() && context.getItemInHand().getItem() == this.asItem() && state.getValue(AMOUNT) < 2 || super.canBeReplaced(state, context);
	}

	private boolean isValidBlock(BlockGetter world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		return blockState.is(BlockTags.LOGS) || blockState.is(MissingWildsModCommon.FALLEN_LOGS);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos placedOnPos = pos.relative(direction.getOpposite());
		BlockState placedOnState = world.getBlockState(placedOnPos);
		return placedOnState.isFaceSturdy(world, pos, direction) && isValidBlock(world, pos.relative(direction.getOpposite()));
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor world, BlockPos blockPos, BlockPos blockPos1) {
		return direction.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(world, blockPos) ? Blocks.AIR.defaultBlockState() : blockState;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, AMOUNT);
	}

}
