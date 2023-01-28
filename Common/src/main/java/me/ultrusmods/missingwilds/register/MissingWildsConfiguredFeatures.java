package me.ultrusmods.missingwilds.register;

import com.google.common.collect.ImmutableList;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.CombinedStackingFlowerBlock;
import me.ultrusmods.missingwilds.block.StackingFlowerBlock;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import me.ultrusmods.missingwilds.worldgen.feature.tree.PolyporeMushroomTreeDecorator;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
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

import java.util.ArrayList;
import java.util.List;


public class MissingWildsConfiguredFeatures {
	static {
		MissingWildsFeatures.init();
	}
	public static final RegistrationProvider<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = RegistrationProvider.get(BuiltinRegistries.CONFIGURED_FEATURE, Constants.MOD_ID);

	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_BIRCH_LOG = registerFallenLog("fallen_birch", MissingWildsBlocks.FALLEN_BIRCH_LOG.get());
	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_SPRUCE_LOG = registerFallenLog("fallen_spruce", MissingWildsBlocks.FALLEN_SPRUCE_LOG.get());
	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_OAK_LOG = registerFallenLog("fallen_oak", MissingWildsBlocks.FALLEN_OAK_LOG.get());
	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_JUNGLE_LOG = registerFallenLog("fallen_jungle", MissingWildsBlocks.FALLEN_JUNGLE_LOG.get());
	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_DARK_OAK_LOG = registerFallenLog("fallen_dark_oak", MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get());
	public static final Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> CONFIGURED_FALLEN_ACACIA_LOG = registerFallenLog("fallen_acacia", MissingWildsBlocks.FALLEN_ACACIA_LOG.get());

	private static final BranchTreeDecorator BIRCH_BRANCH_BEES_0002 = new BranchTreeDecorator(BlockStateProvider.simple(Blocks.BIRCH_LOG), 0.01f, 0.2f);
	private static final BranchTreeDecorator BIRCH_BRANCH = new BranchTreeDecorator(BlockStateProvider.simple(Blocks.BIRCH_LOG), 0.0f, 0.2f);
	private static final BranchTreeDecorator BIRCH_BRANCH_BEES_0005 = new BranchTreeDecorator(BlockStateProvider.simple(Blocks.BIRCH_LOG), 0.05f, 0.2f);

	private static final PolyporeMushroomTreeDecorator POLYPORES = new PolyporeMushroomTreeDecorator(BlockStateProvider.simple(MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM.get()), 0.1f);

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> BIRCH_BEES_0002 = registerConfiguredFeature("birch_bees_0002", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH_BEES_0002, POLYPORES)).build());
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SUPER_BIRCH_BEES_0002 = registerConfiguredFeature("super_birch_bees_0002", Feature.TREE, superBirch().decorators(ImmutableList.of(BIRCH_BRANCH_BEES_0002, POLYPORES)).build());

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SAPLING_TREE = registerConfiguredFeature("sapling_birch", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH)).build());
	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SAPLING_TREE_BEE = registerConfiguredFeature("sapling_birch_bees", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH_BEES_0005)).build());


	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BLUE_FORGET_ME_NOT = createForgetMeNot("blue_forget_me_not", MissingWildsBlocks.BLUE_FORGET_ME_NOT.get());
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PINK_FORGET_ME_NOT = createForgetMeNot("pink_forget_me_not", MissingWildsBlocks.PINK_FORGET_ME_NOT.get());
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WHITE_FORGET_ME_NOT = createForgetMeNot("white_forget_me_not", MissingWildsBlocks.WHITE_FORGET_ME_NOT.get());
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PURPLE_FORGET_ME_NOT = createForgetMeNot("purple_forget_me_not", MissingWildsBlocks.PURPLE_FORGET_ME_NOT.get());
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>>[] FORGET_ME_NOTS = new Holder[4];

	static {
		FORGET_ME_NOTS[0] = BLUE_FORGET_ME_NOT;
		FORGET_ME_NOTS[1] = PURPLE_FORGET_ME_NOT;
		FORGET_ME_NOTS[2] = PINK_FORGET_ME_NOT;
		FORGET_ME_NOTS[3] = WHITE_FORGET_ME_NOT;
	}

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SWEETSPIRE = registerConfiguredFeature("sweetspire", Feature.FLOWER, createRandomPatchFeatureConfig(BlockStateProvider.simple(MissingWildsBlocks.SWEETSPIRE.get()), 32));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> GRASS = registerConfiguredFeature("grass", Feature.FLOWER, createRandomPatchFeatureConfig(BlockStateProvider.simple(Blocks.GRASS), 32));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> TALL_GRASS = registerConfiguredFeature("tall_grass", Feature.FLOWER, createRandomPatchFeatureConfig(BlockStateProvider.simple(Blocks.TALL_GRASS), 32));

	public static void init() {
	}

	private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
	}

	private static TreeConfiguration.TreeConfigurationBuilder birch() {
		return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 7, 1, 2, 2).ignoreVines();
	}

	private static TreeConfiguration.TreeConfigurationBuilder superBirch() {
		return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 9, 2, 6, 2).ignoreVines();
	}

//	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerConfiguredFeature(String id, F feature, FC featureConfig) {
//		return CONFIGURED_FEATURES.<ConfiguredFeature<FC, ?>>register(id, () -> new ConfiguredFeature<>(feature, featureConfig)).asHolder();
//	}
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> registerConfiguredFeature(String id, F feature, FC featureConfig) {
		return FeatureUtils.register(Constants.MOD_ID + ":" + id, feature, featureConfig);
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> registerFallenLog(String id, Block block) {
		return FeatureUtils.register(Constants.MOD_ID + ":" + id, MissingWildsFeatures.FALLEN_LOG.get(), new FallenLogFeatureConfig(BlockStateProvider.simple(block)));
	}

	private static RandomPatchConfiguration createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(block)));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> createForgetMeNot(String name, Block block) {
		// TODO: Can probably do this a better way, at some point.
		if (block instanceof StackingFlowerBlock stackingFlowerBlock) {
			var type = stackingFlowerBlock.getFlowerType();
			var types = new ArrayList<CombinedStackingFlowerBlock.FlowerType>();
			for (var flowerType : CombinedStackingFlowerBlock.FlowerType.values()) {
				if (flowerType != type && flowerType != CombinedStackingFlowerBlock.FlowerType.NONE) {
					types.add(flowerType);
				}
			}
			var simpleWeightedRandomList = SimpleWeightedRandomList.<BlockState>builder();
			for (var flowerType : types) {
				simpleWeightedRandomList.add(MissingWildsBlocks.FORGET_ME_NOT.get().defaultBlockState().setValue(CombinedStackingFlowerBlock.FLOWER_1, type).setValue(CombinedStackingFlowerBlock.FLOWER_2, flowerType), flowerType == type ? 4 : 2);
				for (var flowerType2 : types) {
					simpleWeightedRandomList.add(MissingWildsBlocks.FORGET_ME_NOT.get().defaultBlockState().setValue(CombinedStackingFlowerBlock.FLOWER_1, type).setValue(CombinedStackingFlowerBlock.FLOWER_2, flowerType).setValue(CombinedStackingFlowerBlock.FLOWER_3, flowerType2), flowerType2 == type ? 6 : 3);
				}
			}
			simpleWeightedRandomList.add(block.defaultBlockState(), 6);
			return registerConfiguredFeature(
					name,
					Feature.FLOWER,
					createRandomPatchFeatureConfig(
							new WeightedStateProvider(simpleWeightedRandomList), 64
					)
			);
		} else {
			throw new IllegalArgumentException("Block must be a StackingFlowerBlock");
		}
	}
}
