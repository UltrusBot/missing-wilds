package me.ultrusmods.missingwilds.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import org.jetbrains.annotations.Nullable;

public class StackingFlowerBlock extends FlowerBlock {
	public static final IntProperty FLOWERS = IntProperty.of("flowers", 1, 3);

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
