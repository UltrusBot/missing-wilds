package me.ultrusmods.missingwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StackingFlowerBlock extends FlowerBlock {
	public static final IntegerProperty FLOWERS = IntegerProperty.create("flowers", 1, 3);
	protected static final VoxelShape SMALLER_SHAPE = Block.box(5.5, 0.0, 5.5, 10.5, 4.0, 10.5);
	protected static final VoxelShape SMALL_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
	protected static final VoxelShape REGULAR_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(world, pos);
		return switch (state.getValue(FLOWERS)) {
			case 3 -> REGULAR_SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
			case 2 -> SMALL_SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
			default -> SMALLER_SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
		};
	}
	public StackingFlowerBlock(MobEffect statusEffect, int i, Properties settings) {
		super(statusEffect, i, settings);
		this.registerDefaultState(this.defaultBlockState().setValue(FLOWERS, 1));

	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
		if (blockState.is(this)) {
			return blockState.cycle(FLOWERS);
		}
		return super.getStateForPlacement(ctx);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return !context.isSecondaryUseActive() && context.getItemInHand().getItem() == this.asItem() && state.getValue(FLOWERS) < 3 || super.canBeReplaced(state, context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FLOWERS);
	}
}
