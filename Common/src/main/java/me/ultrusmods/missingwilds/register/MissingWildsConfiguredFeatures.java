package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.CombinedStackingFlowerBlock;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.Arrays;
import java.util.List;


public class MissingWildsConfiguredFeatures {
	static {
		MissingWildsFeatures.init();
	}
	private static final BranchTreeDecorator BIRCH_BRANCH = new BranchTreeDecorator(BlockStateProvider.simple(Blocks.BIRCH_LOG), 0.0f, 0.2f);
	private static final BranchTreeDecorator BIRCH_BRANCH_BEES_0005 = new BranchTreeDecorator(BlockStateProvider.simple(Blocks.BIRCH_LOG), 0.05f, 0.2f);
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SAPLING_TREE = registerConfiguredFeature("sapling_birch", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH)).build());
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SAPLING_TREE_BEE = registerConfiguredFeature("sapling_birch_bees", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH_BEES_0005)).build());

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FORGET_ME_NOT_BONEMEAL = createForgetMeNotBonemeal("forget_me_not_bonemeal");

	private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
	}

	private static TreeConfiguration.TreeConfigurationBuilder birch() {
		return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 7, 1, 2, 2).ignoreVines();
	}
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> createForgetMeNotBonemeal(String name) {
			var simpleWeightedRandomList = SimpleWeightedRandomList.<BlockState>builder();
			simpleWeightedRandomList.add(MissingWildsBlocks.BLUE_FORGET_ME_NOT.get().defaultBlockState(), 3);
			simpleWeightedRandomList.add(MissingWildsBlocks.PINK_FORGET_ME_NOT.get().defaultBlockState(), 3);
			simpleWeightedRandomList.add(MissingWildsBlocks.PURPLE_FORGET_ME_NOT.get().defaultBlockState(), 3);
			simpleWeightedRandomList.add(MissingWildsBlocks.WHITE_FORGET_ME_NOT.get().defaultBlockState(), 3);
			List<CombinedStackingFlowerBlock.FlowerType> flowerTypes = Arrays.stream(CombinedStackingFlowerBlock.FlowerType.values())
				.filter(flowerType -> flowerType != CombinedStackingFlowerBlock.FlowerType.NONE)
				.toList();

			for (CombinedStackingFlowerBlock.FlowerType flowerType1 : flowerTypes) {
				for (CombinedStackingFlowerBlock.FlowerType flowerType2 : flowerTypes) {
					if (flowerType1 != flowerType2) {
						simpleWeightedRandomList.add(MissingWildsBlocks.FORGET_ME_NOT.get().defaultBlockState().setValue(CombinedStackingFlowerBlock.FLOWER_1, flowerType1).setValue(CombinedStackingFlowerBlock.FLOWER_2, flowerType2), 2);
						for (CombinedStackingFlowerBlock.FlowerType flowerType3 : flowerTypes) {
							if (flowerType3 != flowerType1 && flowerType3 != flowerType2) {
								simpleWeightedRandomList.add(MissingWildsBlocks.FORGET_ME_NOT.get().defaultBlockState().setValue(CombinedStackingFlowerBlock.FLOWER_1, flowerType1).setValue(CombinedStackingFlowerBlock.FLOWER_2, flowerType2).setValue(CombinedStackingFlowerBlock.FLOWER_3, flowerType3), 1);
							}
						}
					}
				}
			}

			return registerConfiguredFeature(
					name,
					Feature.FLOWER,
					FeatureUtils.simpleRandomPatchConfiguration(32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(simpleWeightedRandomList))))
			);

	}
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerConfiguredFeature(String id, F feature, FC featureConfig) {
		return FeatureUtils.register(Constants.MOD_ID + ":" + id, feature, featureConfig);
	}

	public static void init() {
	}
}
