package me.ultrusmods.missingwilds.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class BetterStackingFlowerBlock extends FlowerBlock {
    public static final EnumProperty<FlowerType> FLOWER_1 = EnumProperty.create("flower_1", FlowerType.class);
    public static final EnumProperty<FlowerType> FLOWER_2 = EnumProperty.create("flower_2", FlowerType.class);
    public static final EnumProperty<FlowerType> FLOWER_3 = EnumProperty.create("flower_3", FlowerType.class);
    public BetterStackingFlowerBlock(MobEffect $$0, int $$1, Properties $$2) {
        super($$0, $$1, $$2);
        this.registerDefaultState(this.defaultBlockState().setValue(FLOWER_1, FlowerType.BLUE).setValue(FLOWER_2, FlowerType.NONE).setValue(FLOWER_3, FlowerType.NONE));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLOWER_1, FLOWER_2, FLOWER_3);
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
