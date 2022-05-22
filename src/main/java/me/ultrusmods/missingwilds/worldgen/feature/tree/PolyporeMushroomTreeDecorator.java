package me.ultrusmods.missingwilds.worldgen.feature.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.block.PolyporeMushroomBlock;
import me.ultrusmods.missingwilds.register.MissingWildsFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class PolyporeMushroomTreeDecorator extends TreeDecorator {
    public static final Codec<PolyporeMushroomTreeDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            BlockStateProvider.TYPE_CODEC.fieldOf("state_provider").forGetter(polyporeMushroomTreeDecorator -> polyporeMushroomTreeDecorator.blockStateProvider),
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
    protected TreeDecoratorType<?> getType() {
        return MissingWildsFeatures.POLYPORE_MUSHROOM;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        for (BlockPos blockPos : logPositions) {
            if (random.nextFloat() < probability) {
                Direction direction = Direction.fromHorizontal(random.nextInt());
                BlockPos polyporePos = blockPos.offset(direction);
                if (Feature.isAir(world, polyporePos)) {
                    BlockState polypore = blockStateProvider.getBlockState(random, polyporePos);
                    if (polypore.contains(Properties.HORIZONTAL_FACING)) {
                        polypore = polypore.with(Properties.HORIZONTAL_FACING, direction);
                    }
                    if (polypore.contains(PolyporeMushroomBlock.AMOUNT)) {
                        polypore = polypore.with(PolyporeMushroomBlock.AMOUNT, random.nextInt(2) + 1);
                    }
                    replacer.accept(polyporePos, polypore);
                }

            }
        }
    }
}
