package me.ultrusmods.missingwilds.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class StackingFlowerBlock extends FlowerBlock {
	public static final IntProperty FLOWERS = IntProperty.of("flowers", 1, 3);
	protected static final VoxelShape SMALLER_SHAPE = Block.createCuboidShape(5.5, 0.0, 5.5, 10.5, 4.0, 10.5);
	protected static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
	protected static final VoxelShape REGULAR_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		switch (state.get(FLOWERS)) {
			case 3 -> {
				return REGULAR_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
			}
			case 2 -> {
				return SMALL_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
			}
			default -> {
				return SMALLER_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
			}
		}
	}
	public StackingFlowerBlock(StatusEffect statusEffect, int i, Settings settings) {
		super(statusEffect, i, settings);
		this.setDefaultState(this.getDefaultState().with(FLOWERS, 1));

	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
		if (blockState.isOf(this)) {
			return blockState.cycle(FLOWERS);
		}
		return super.getPlacementState(ctx);
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(FLOWERS) < 3 || super.canReplace(state, context);
	}

	@Override
	public OffsetType getOffsetType() {
		return OffsetType.NONE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FLOWERS);
	}
}
