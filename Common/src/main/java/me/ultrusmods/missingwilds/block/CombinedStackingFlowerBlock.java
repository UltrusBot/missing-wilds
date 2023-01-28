




package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CombinedStackingFlowerBlock extends FlowerBlock {
    public static final EnumProperty<FlowerType> FLOWER_1 = EnumProperty.create("flower_1", FlowerType.class, (a) -> a != FlowerType.NONE);
    public static final EnumProperty<FlowerType> FLOWER_2 = EnumProperty.create("flower_2", FlowerType.class, (a) -> a != FlowerType.NONE);
    public static final EnumProperty<FlowerType> FLOWER_3 = EnumProperty.create("flower_3", FlowerType.class);
    protected static final VoxelShape SHAPE_2 = Block.box(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
    protected static final VoxelShape SHAPE_3 = Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);

    public CombinedStackingFlowerBlock(MobEffect $$0, int $$1, Properties $$2) {
        super($$0, $$1, $$2);
        this.registerDefaultState(this.defaultBlockState().setValue(FLOWER_1, FlowerType.BLUE).setValue(FLOWER_2, FlowerType.BLUE).setValue(FLOWER_3, FlowerType.NONE));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(FLOWER_3) == FlowerType.NONE ? SHAPE_2 : SHAPE_3;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLOWER_1, FLOWER_2, FLOWER_3);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && (context.getItemInHand().is(MissingWildsTags.FORGET_ME_NOTS) && state.getValue(FLOWER_3) == FlowerType.NONE)|| super.canBeReplaced(state, context);
    }


    public enum FlowerType implements StringRepresentable {
        NONE("none"),
        BLUE("blue"),
        PURPLE("purple"),
        PINK("pink"),
        WHITE("white");
        private final String flowerName;
        FlowerType(String name) {
            this.flowerName = name;
        }

        @Override
        public String getSerializedName() {
            return flowerName;
        }
    }
}
