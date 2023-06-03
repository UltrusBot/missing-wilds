package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FallenLogBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final EnumProperty<Cover> COVER = EnumProperty.create("cover", Cover.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	private static final VoxelShape INSIDE_Z = box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D);
	private static final VoxelShape SHAPE_Z = Shapes.join(Shapes.block(), INSIDE_Z, BooleanOp.ONLY_FIRST);

	private static final VoxelShape INSIDE_X = box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);
	private static final VoxelShape SHAPE_X = Shapes.join(Shapes.block(), INSIDE_X, BooleanOp.ONLY_FIRST);

	private static final VoxelShape INSIDE_Y = box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	private static final VoxelShape SHAPE_Y = Shapes.join(Shapes.block(), INSIDE_Y, BooleanOp.ONLY_FIRST);


	public FallenLogBlock(Properties settings)  {
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(COVER, Cover.NONE).setValue(WATERLOGGED, false));

	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return direction == Direction.UP ?
				state.setValue(COVER, getCover(neighborState)) :
				super.updateShape(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Direction.Axis axis = state.getValue(AXIS);
		switch (axis) {
			case X -> {
				return SHAPE_X;
			}
			case Y -> {
				return SHAPE_Y;
			}
			case Z -> {
				return SHAPE_Z;
			}
		}
		return SHAPE_Y;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return RotatedPillarBlock.rotatePillar(state, rotation);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS, COVER, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockstate = ctx.getLevel().getBlockState(ctx.getClickedPos().above());
		FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
		return this.defaultBlockState().setValue(AXIS, ctx.getClickedFace().getAxis()).setValue(COVER, getCover(blockstate)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	private static Cover getCover(BlockState state) {
		if (state.is(MissingWildsTags.MOSS)) {
			return Cover.MOSSY;
		} else if (state.is(MissingWildsTags.SNOW)) {
			return Cover.SNOWY;
		} else {
			return Cover.NONE;
		}
	}

	public enum Cover implements StringRepresentable {
		NONE("none"),
		MOSSY("moss"),
		SNOWY("snow");
		final String name;
		Cover(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
