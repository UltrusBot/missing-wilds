package me.ultrusmods.missingwilds.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class FallenLogBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty MOSSY = BooleanProperty.of("mossy");
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	private static final VoxelShape TOP = Block.createCuboidShape(0, 0.0, 0, 16.0, 2.0, 16.0);
	private static final VoxelShape BOTTOM = Block.createCuboidShape(0, 14.0, 0, 16.0, 16.0, 16.0);

	private static final VoxelShape LEFT_Z = Block.createCuboidShape(0, 2, 0, 2, 14, 16);
	private static final VoxelShape RIGHT_Z = Block.createCuboidShape(14, 2, 0, 16, 14, 16);

	private static final VoxelShape LEFT_X = Block.createCuboidShape(0, 2, 0, 16, 14, 2);
	private static final VoxelShape RIGHT_X = Block.createCuboidShape(0, 2, 14, 16, 14, 16);

	private static final VoxelShape SHAPE_X = VoxelShapes.union(TOP, BOTTOM, RIGHT_X, LEFT_X);
	private static final VoxelShape SHAPE_Z = VoxelShapes.union(TOP, BOTTOM, RIGHT_Z, LEFT_Z);

	public FallenLogBlock(Settings settings)  {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(MOSSY, false).with(WATERLOGGED, false));

	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return direction == Direction.UP ?
				state.with(MOSSY, isMossBlock(neighborState)) :
				super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = state.get(FACING);
		return direction.getAxis() == Direction.Axis.X ? SHAPE_X : SHAPE_Z;
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
		builder.add(FACING, MOSSY, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockstate = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(MOSSY, isMossBlock(blockstate)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	private static boolean isMossBlock(BlockState state) {
		// TODO: Make a tag for this?
		return state.isOf(Blocks.MOSS_CARPET) || state.isOf(Blocks.MOSS_BLOCK);
	}
}
