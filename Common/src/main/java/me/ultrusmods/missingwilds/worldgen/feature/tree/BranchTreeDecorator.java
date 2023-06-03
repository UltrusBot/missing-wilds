package me.ultrusmods.missingwilds.worldgen.feature.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.register.MissingWildsFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class BranchTreeDecorator extends TreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(branchTreeDecorator -> branchTreeDecorator.branchProvider),
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
    protected TreeDecoratorType<?> type() {
        return MissingWildsFeatures.BRANCH_TREE.get();
    }


    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        var world = context.level();
        var logPositions = context.logs();

        if (random.nextFloat() >= this.branchProbability) return;
        int maxY = logPositions.get(logPositions.size() - 1).getY();
        int minY = logPositions.get(0).getY();
        boolean generatedBranch = false;
        for (BlockPos blockPos : logPositions) {
            if (generatedBranch) break;
            if (blockPos.getY() < (maxY + minY)/2) continue;
            Direction direction = Direction.from2DDataValue(random.nextInt());
            BlockPos checkPos = blockPos.relative(direction);
            if (context.isAir(checkPos)) {
                BlockState state = branchProvider.getState(random, checkPos);
                if (state.hasProperty(BlockStateProperties.AXIS)) {
                    state = state.setValue(BlockStateProperties.AXIS, direction.getAxis());
                }
                generatedBranch = true;
                context.setBlock(checkPos, state);
            }
            BlockPos downPos = checkPos.below();
            if (context.isAir(downPos) && random.nextFloat() < this.beeProbability) {
                context.setBlock(downPos, Blocks.BEE_NEST.defaultBlockState().setValue(BeehiveBlock.FACING, direction));
                world.getBlockEntity(downPos, BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
                    int i = 2 + random.nextInt(2);
                    for (int j = 0; j < i; ++j) {
                        CompoundTag nbtCompound = new CompoundTag();
                        nbtCompound.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.BEE).toString());
                        blockEntity.storeBee(nbtCompound, random.nextInt(599), false);
                    }
                });
            }

        }
    }
}
