package me.ultrusmods.missingwilds.worldgen.feature;

import com.mojang.serialization.Codec;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class FallenLogFeature extends Feature<FallenLogFeatureConfig> {
	public static BlockState MOSS = Blocks.MOSS_CARPET.getDefaultState();

	public FallenLogFeature(Codec<FallenLogFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<FallenLogFeatureConfig> context) {
		BlockPos blockPos = context.getOrigin();
		StructureWorldAccess structureWorldAccess = context.getWorld();
		Random random = context.getRandom();
		FallenLogFeatureConfig fallenLogFeatureConfig = context.getConfig();
		BlockState blockState = fallenLogFeatureConfig.stateProvider.getBlockState(random, blockPos);

		int size = random.nextInt(3, 7);
		Direction direction = Direction.byId(random.nextInt(2, 6));
		if (blockState.contains(Properties.HORIZONTAL_FACING)) {
			blockState = blockState.with(FallenLogBlock.FACING, direction);
		}
		boolean validSpot = true;
		for (int i = 0; i < size; i++) {
			BlockPos cur = blockPos.offset(direction, i);
			validSpot = validSpot && structureWorldAccess.isAir(cur) && structureWorldAccess.getBlockState(cur.down()).isIn(BlockTags.DIRT);
		}
		if (validSpot) {
			for (int i = 0; i < size; i++) {
				BlockPos cur = blockPos.offset(direction, i);
				BlockPos above = cur.up();
				boolean mossy = random.nextFloat() > .33 && structureWorldAccess.isAir(above);
				structureWorldAccess.setBlockState(cur, blockState.with(FallenLogBlock.MOSSY, mossy), 4);
				if (mossy) {
					structureWorldAccess.setBlockState(above, MOSS, 4);
				}
			}
		}
		return validSpot;
	}
}
