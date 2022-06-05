package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.MissingWildsMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PolyporeMushroomBlock extends Block {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final IntProperty AMOUNT = IntProperty.of("amount", 1, 2);

	private static final VoxelShape SOUTH_ONE = Block.createCuboidShape(2, 9.0, 0, 14.0, 12.0, 6.0);
	private static final VoxelShape SOUTH_TWO = Block.createCuboidShape(0, 7.0, 0, 16.0, 12.0, 7.0);
	private static final VoxelShape EAST_ONE = Block.createCuboidShape(0, 9.0, 2, 6.0, 12.0, 14.0);
	private static final VoxelShape EAST_TWO = Block.createCuboidShape(0, 7.0, 0, 7.0, 12.0, 16.0);
	private static final VoxelShape NORTH_ONE = Block.createCuboidShape(2, 9.0, 10, 14.0, 12.0, 16);
	private static final VoxelShape NORTH_TWO = Block.createCuboidShape(0, 7.0, 9, 16.0, 12.0, 16);
	private static final VoxelShape WEST_ONE = Block.createCuboidShape(10, 9.0, 2, 16, 12.0, 14.0);
	private static final VoxelShape WEST_TWO = Block.createCuboidShape(9, 7.0, 0, 16, 12.0, 16.0);

	public PolyporeMushroomBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AMOUNT, 1));

	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		switch(state.get(FACING)) {
			case SOUTH -> {
				return state.get(AMOUNT) == 1 ? SOUTH_ONE : SOUTH_TWO;
			}
			case NORTH -> {
				return state.get(AMOUNT) == 1 ? NORTH_ONE : NORTH_TWO;
			}
			case EAST -> {
				return state.get(AMOUNT) == 1 ? EAST_ONE : EAST_TWO;
			}
			case WEST -> {
				return state.get(AMOUNT) == 1 ? WEST_ONE : WEST_TWO;
			}
		}
		return super.getOutlineShape(state, world, pos, context);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (random.nextInt(50) == 0 && state.get(AMOUNT) == 1) {
			world.setBlockState(pos, state.with(AMOUNT, 2), Block.NOTIFY_LISTENERS);
		}
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
		if (blockState.isOf(this)) {
			return blockState.cycle(AMOUNT);
		}

		for(Direction direction : ctx.getPlacementDirections()) {
			if (direction.getAxis().isHorizontal()) {
				return getDefaultState().with(FACING, direction.getOpposite());
			}
		}

		return super.getPlacementState(ctx);
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(AMOUNT) < 2 || super.canReplace(state, context);
	}

	private boolean canPlaceOn(BlockView world, BlockPos pos, Direction side) {
		BlockState blockState = world.getBlockState(pos);
		return blockState.isSideSolidFullSquare(world, pos, side) && blockState.isIn(BlockTags.LOGS) || blockState.isIn(MissingWildsMod.FALLEN_LOGS);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		return this.canPlaceOn(world, pos.offset(direction.getOpposite()), direction);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, AMOUNT);
	}

}
