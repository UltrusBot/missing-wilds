package me.ultrusmods.missingwilds.worldgen.feature;

import com.mojang.serialization.Codec;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import java.util.Random;

public class FallenLogFeature extends Feature<FallenLogFeatureConfig> {
	public static BlockState MOSS = Blocks.MOSS_CARPET.defaultBlockState();

	public FallenLogFeature(Codec<FallenLogFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<FallenLogFeatureConfig> context) {
		BlockPos blockPos = context.origin();
		WorldGenLevel structureWorldAccess = context.level();
		Random random = context.random();
		FallenLogFeatureConfig fallenLogFeatureConfig = context.config();
		BlockState blockState = fallenLogFeatureConfig.stateProvider.getState(random, blockPos);

		int size = random.nextInt(3, 7);
		Direction.Axis axis = Direction.Axis.getRandom(random);
		if (blockState.hasProperty(BlockStateProperties.AXIS)) {
			blockState = blockState.setValue(FallenLogBlock.AXIS, axis);
		}
		boolean validSpot = true;
		for (int i = 0; i < size; i++) {
			BlockPos cur = blockPos.relative(axis, i);
			validSpot = validSpot && structureWorldAccess.isEmptyBlock(cur) && structureWorldAccess.getBlockState(cur.below()).is(BlockTags.DIRT);
		}
		if (validSpot) {
			for (int i = 0; i < size; i++) {
				BlockPos cur = blockPos.relative(axis, i);
				BlockPos above = cur.above();
				boolean mossy = random.nextFloat() > .33 && structureWorldAccess.isEmptyBlock(above);
				structureWorldAccess.setBlock(cur, blockState.setValue(FallenLogBlock.MOSSY, mossy), 4);
				if (mossy) {
					structureWorldAccess.setBlock(above, MOSS, 4);
				}
			}
		}
		return validSpot;
	}
}
