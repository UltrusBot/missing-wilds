package me.ultrusmods.missingwilds.register;

import com.google.common.collect.ImmutableList;
import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.block.StackingFlowerBlock;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import me.ultrusmods.missingwilds.worldgen.feature.tree.PolyporeMushroomTreeDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;


public class MissingWildsConfiguredFeatures {
	public static final RegistryEntry<ConfiguredFeature<?, ?>> CONFIGURED_FALLEN_BIRCH_LOG = registerConfiguredFeature(
			"fallen_birch", MissingWildsFeatures.FALLEN_LOG, new FallenLogFeatureConfig(BlockStateProvider.of(MissingWildsBlocks.FALLEN_BIRCH_LOG))
	);

	private static final BranchTreeDecorator BIRCH_BRANCH_BEES_0002 = new BranchTreeDecorator(BlockStateProvider.of(Blocks.BIRCH_LOG), 0.01f, 0.2f);
	private static final PolyporeMushroomTreeDecorator POLYPORES = new PolyporeMushroomTreeDecorator(BlockStateProvider.of(MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM), 0.1f);

	public static final RegistryEntry<ConfiguredFeature<?, ?>> BIRCH_BEES_0002 = registerConfiguredFeature("birch_bees_0002", Feature.TREE, birch().decorators(List.of(BIRCH_BRANCH_BEES_0002, POLYPORES)).build());
	public static final RegistryEntry<ConfiguredFeature<?, ?>> SUPER_BIRCH_BEES_0002 = registerConfiguredFeature("super_birch_bees_0002", Feature.TREE, superBirch().decorators(ImmutableList.of(BIRCH_BRANCH_BEES_0002, POLYPORES)).build());

	public static final RegistryEntry<ConfiguredFeature<?, ?>> BLUE_FORGET_ME_NOT = createForgetMeNot("blue_forget_me_not", MissingWildsBlocks.BLUE_FORGET_ME_NOT);
	public static final RegistryEntry<ConfiguredFeature<?, ?>> PURPLE_FORGET_ME_NOT = createForgetMeNot("purple_forget_me_not", MissingWildsBlocks.PURPLE_FORGET_ME_NOT);
	public static final RegistryEntry<ConfiguredFeature<?, ?>> PINK_FORGET_ME_NOT = createForgetMeNot("pink_forget_me_not", MissingWildsBlocks.PINK_FORGET_ME_NOT);
	public static final RegistryEntry<ConfiguredFeature<?, ?>> WHITE_FORGET_ME_NOT = createForgetMeNot("white_forget_me_not", MissingWildsBlocks.WHITE_FORGET_ME_NOT);

	public static void init() {

	}

	private static TreeFeatureConfig.Builder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
		return new TreeFeatureConfig.Builder(BlockStateProvider.of(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.of(leaves), new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3), new TwoLayersFeatureSize(1, 0, 1));
	}

	private static TreeFeatureConfig.Builder birch() {
		return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 5, 2, 4, 2).ignoreVines();
	}

	private static TreeFeatureConfig.Builder superBirch() {
		return builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 8, 2, 6, 2).ignoreVines();
	}

	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id, F feature, FC featureConfig) {
		return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, MissingWildsMod.id(id), new ConfiguredFeature(feature, featureConfig));
	}

	private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
		return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
	}
	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<?, ?>> createForgetMeNot(String name, Block block) {
		return registerConfiguredFeature(
				name,
				Feature.FLOWER,
				createRandomPatchFeatureConfig(
						new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(block.getDefaultState().with(StackingFlowerBlock.FLOWERS, 3), 3).add(block.getDefaultState().with(StackingFlowerBlock.FLOWERS, 2), 2).add(block.getDefaultState().with(StackingFlowerBlock.FLOWERS, 1), 1)), 64
				)
		);
	}
}
