package me.ultrusmods.missingwilds.worldgen.feature.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.block.PolyporeMushroomBlock;
import me.ultrusmods.missingwilds.register.MissingWildsFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class PolyporeMushroomTreeDecorator extends TreeDecorator {
    public static final Codec<PolyporeMushroomTreeDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(polyporeMushroomTreeDecorator -> polyporeMushroomTreeDecorator.blockStateProvider),
                            Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(polyporeMushroomTreeDecorator -> polyporeMushroomTreeDecorator.probability)
                    )
                    .apply(instance, PolyporeMushroomTreeDecorator::new)
    );
    private final float probability;
    private final BlockStateProvider blockStateProvider;

    public PolyporeMushroomTreeDecorator(BlockStateProvider blockStateProvider, float probability) {
        this.probability = probability;
        this.blockStateProvider = blockStateProvider;
    }
    
    @Override
    protected TreeDecoratorType<?> type() {
        return MissingWildsFeatures.POLYPORE_MUSHROOM.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        var logPositions = context.logs();
        for (BlockPos blockPos : logPositions) {
            if (random.nextFloat() < probability) {
                Direction direction = Direction.from2DDataValue(random.nextInt());
                BlockPos polyporePos = blockPos.relative(direction);
                if (context.isAir(polyporePos)) {
                    BlockState polypore = blockStateProvider.getState(random, polyporePos);
                    if (polypore.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                        polypore = polypore.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
                    }
                    if (polypore.hasProperty(PolyporeMushroomBlock.AMOUNT)) {
                        polypore = polypore.setValue(PolyporeMushroomBlock.AMOUNT, random.nextInt(2) + 1);
                    }
                    context.setBlock(polyporePos, polypore);
                }

            }
        }
    }
}
