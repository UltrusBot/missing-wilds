package me.ultrusmods.missingwilds.worldgen.feature.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.register.MissingWildsFeatures;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BranchTreeDecorator extends TreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            BlockStateProvider.TYPE_CODEC.fieldOf("state_provider").forGetter(branchTreeDecorator -> branchTreeDecorator.branchProvider),
                            Codec.floatRange(0.0f, 1.0f).fieldOf("bee_probability").forGetter(branchTreeDecorator -> branchTreeDecorator.beeProbability),
                            Codec.floatRange(0.0f, 1.0f).fieldOf("branch_probability").forGetter(branchTreeDecorator -> branchTreeDecorator.branchProbability)
                    )
                    .apply(instance, BranchTreeDecorator::new)
    );

    public final BlockStateProvider branchProvider;
    public final float beeProbability;
    public final float branchProbability;

    public BranchTreeDecorator(BlockStateProvider branchProvider, float beeProbability, float branchProbability) {
        this.branchProvider = branchProvider;
        this.beeProbability = beeProbability;
        this.branchProbability = branchProbability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return MissingWildsFeatures.BRANCH_TREE;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        if (random.nextFloat() >= this.branchProbability) return;
        int maxY = logPositions.get(logPositions.size() - 1).getY();
        int minY = logPositions.get(0).getY();
        boolean generatedBranch = false;
        for (BlockPos blockPos : logPositions) {
            if (generatedBranch) break;
            if (blockPos.getY() < (maxY + minY)/2) continue;
            Direction direction = Direction.fromHorizontal(random.nextInt());
            BlockPos checkPos = blockPos.offset(direction);
            if (Feature.isAir(world, checkPos)) {
                BlockState state = branchProvider.getBlockState(random, checkPos);
                if (state.contains(Properties.AXIS)) {
                    state = state.with(Properties.AXIS, direction.getAxis());
                }
                generatedBranch = true;
                replacer.accept(checkPos, state);
            }
            BlockPos downPos = checkPos.down();
            if (Feature.isAir(world, downPos) && random.nextFloat() < this.beeProbability) {
                replacer.accept(downPos, Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, direction));
                world.getBlockEntity(downPos, BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
                    int i = 2 + random.nextInt(2);
                    for (int j = 0; j < i; ++j) {
                        NbtCompound nbtCompound = new NbtCompound();
                        nbtCompound.putString("id", Registry.ENTITY_TYPE.getId(EntityType.BEE).toString());
                        blockEntity.addBee(nbtCompound, random.nextInt(599), false);
                    }
                });
            }

        }
    }
}
