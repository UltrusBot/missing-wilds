package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static me.ultrusmods.missingwilds.block.CombinedStackingFlowerBlock.*;

public class StackingFlowerBlock extends FlowerBlock {
	protected static final VoxelShape SMALLER_SHAPE = Block.box(5.5, 0.0, 5.5, 10.5, 4.0, 10.5);
	private final CombinedStackingFlowerBlock.FlowerType flowerType;

	public StackingFlowerBlock(MobEffect statusEffect, int i, Properties settings, CombinedStackingFlowerBlock.FlowerType flowerType) {
		super(statusEffect, i, settings);
		this.flowerType = flowerType;
	}

	@Override
	public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
		return SMALLER_SHAPE;
	}

	public CombinedStackingFlowerBlock.FlowerType getFlowerType() {
		return flowerType;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
		if (blockState.getBlock() instanceof StackingFlowerBlock stackingFlowerBlock) {
			return MissingWildsBlocks.FORGET_ME_NOT.get().defaultBlockState().setValue(FLOWER_1, stackingFlowerBlock.flowerType).setValue(FLOWER_2, this.flowerType);
		}
		if (blockState.getBlock() instanceof CombinedStackingFlowerBlock) {
			if (blockState.getValue(FLOWER_3) == FlowerType.NONE) {
				return blockState.setValue(FLOWER_3, this.flowerType);
			}
		}
		return super.getStateForPlacement(ctx);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return !context.isSecondaryUseActive() && (context.getItemInHand().is(MissingWildsTags.FORGET_ME_NOTS))|| super.canBeReplaced(state, context);
	}
}
