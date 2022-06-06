package me.ultrusmods.missingwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FallenLogBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty MOSSY = BooleanProperty.create("mossy");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private static final VoxelShape TOP = Block.box(0, 0.0, 0, 16.0, 2.0, 16.0);
	private static final VoxelShape BOTTOM = Block.box(0, 14.0, 0, 16.0, 16.0, 16.0);

	private static final VoxelShape LEFT_Z = Block.box(0, 2, 0, 2, 14, 16);
	private static final VoxelShape RIGHT_Z = Block.box(14, 2, 0, 16, 14, 16);

	private static final VoxelShape LEFT_X = Block.box(0, 2, 0, 16, 14, 2);
	private static final VoxelShape RIGHT_X = Block.box(0, 2, 14, 16, 14, 16);

	private static final VoxelShape SHAPE_X = Shapes.or(TOP, BOTTOM, RIGHT_X, LEFT_X);
	private static final VoxelShape SHAPE_Z = Shapes.or(TOP, BOTTOM, RIGHT_Z, LEFT_Z);

	public FallenLogBlock(Properties settings)  {
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(MOSSY, false).setValue(WATERLOGGED, false));

	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return direction == Direction.UP ?
				state.setValue(MOSSY, isMossBlock(neighborState)) :
				super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(FACING);
		return direction.getAxis() == Direction.Axis.X ? SHAPE_X : SHAPE_Z;
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
		builder.add(FACING, MOSSY, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockstate = ctx.getLevel().getBlockState(ctx.getClickedPos().above());
		FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
		return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(MOSSY, isMossBlock(blockstate)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	private static boolean isMossBlock(BlockState state) {
		// TODO: Make a tag for this?
		return state.is(Blocks.MOSS_CARPET) || state.is(Blocks.MOSS_BLOCK);
	}
}
