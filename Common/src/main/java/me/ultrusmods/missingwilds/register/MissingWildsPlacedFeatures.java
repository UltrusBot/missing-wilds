package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MissingWildsPlacedFeatures {
	public static final RegistrationProvider<PlacedFeature> PLACED_FEATURES = RegistrationProvider.get(BuiltinRegistries.PLACED_FEATURE, Constants.MOD_ID);
	public static final Holder<PlacedFeature> PLACED_FALLEN_BIRCH_LOG = createFallenLog("fallen_birch", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_BIRCH_LOG);
	public static final Holder<PlacedFeature> PLACED_FALLEN_SPRUCE_LOG = createFallenLog("fallen_spruce", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_SPRUCE_LOG);
	public static final Holder<PlacedFeature> PLACED_FALLEN_OAK_LOG = createFallenLog("fallen_oak", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_OAK_LOG);
	public static final Holder<PlacedFeature> PLACED_FALLEN_JUNGLE_LOG = createFallenLog("fallen_jungle", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_JUNGLE_LOG);
	public static final Holder<PlacedFeature> PLACED_FALLEN_DARK_OAK_LOG = createFallenLog("fallen_dark_oak", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_DARK_OAK_LOG);
	public static final Holder<PlacedFeature> PLACED_FALLEN_ACACIA_LOG = createFallenLog("fallen_acacia", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_ACACIA_LOG);

	public static final Holder<PlacedFeature> BLUE_FORGET_ME_NOT = createForgetMeNot("blue_forget_me_not", MissingWildsConfiguredFeatures.BLUE_FORGET_ME_NOT, 20);
	public static final Holder<PlacedFeature> PURPLE_FORGET_ME_NOT = createForgetMeNot("purple_forget_me_not", MissingWildsConfiguredFeatures.PURPLE_FORGET_ME_NOT, 20);
	public static final Holder<PlacedFeature> PINK_FORGET_ME_NOT = createForgetMeNot("pink_forget_me_not", MissingWildsConfiguredFeatures.PINK_FORGET_ME_NOT, 20);
	public static final Holder<PlacedFeature> WHITE_FORGET_ME_NOT = createForgetMeNot("white_forget_me_not", MissingWildsConfiguredFeatures.WHITE_FORGET_ME_NOT, 32);
	public static final Holder<PlacedFeature> SWEETSPIRE = createRares("sweetspire", MissingWildsConfiguredFeatures.SWEETSPIRE, 10);
	public static final Holder<PlacedFeature> TALL_GRASS = createRares("tall_grass", MissingWildsConfiguredFeatures.TALL_GRASS, 10);
	public static final Holder<PlacedFeature> GRASS = createGrass("grass", MissingWildsConfiguredFeatures.GRASS, 5, 10);
	public static final Holder<PlacedFeature> TREES_BIRCH = registerPlacedFeature(
			"trees_birch", MissingWildsConfiguredFeatures.BIRCH_BEES_0002,
			VegetationPlacements.treePlacement(PlacementUtils.countExtra(15, 0.1f, 3),
					Blocks.BIRCH_SAPLING)
	);
	public static final Holder<PlacedFeature> TALL_TREES_BIRCH = registerPlacedFeature(
			"tall_trees_birch", MissingWildsConfiguredFeatures.SUPER_BIRCH_BEES_0002,
			VegetationPlacements.treePlacement(PlacementUtils.countExtra(15, 0.1f, 3),
					Blocks.BIRCH_SAPLING)
	);

	public static void init() {

	}

	public static Holder<PlacedFeature> registerPlacedFeature(String id, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
//		return PLACED_FEATURES.register(id, () -> new PlacedFeature(Holder.hackyErase(configuredFeature), List.copyOf(List.of(modifiers)))).asHolder();
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, id), new PlacedFeature(Holder.hackyErase(configuredFeature), List.copyOf(List.of(modifiers))));

	}

	public static Holder<PlacedFeature> registerPlacedFeature(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
//		return PLACED_FEATURES.register(id, () -> new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers))).asHolder();
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, id), new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers)));

	}

	public static Holder<PlacedFeature> createFallenLog(String id, Holder<ConfiguredFeature<FallenLogFeatureConfig, ?>> configuredFeature) {
		return registerPlacedFeature(id, configuredFeature, PlacementUtils.filteredByBlockSurvival(MissingWildsBlocks.FALLEN_BIRCH_LOG.get()),
				CountPlacement.of(1),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
	}

	public static Holder<PlacedFeature> createRares(String name, Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> configuredFeatureRegistryEntry, int chance) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				RarityFilter.onAverageOnceEvery(chance),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BiomeFilter.biome()
		);
	}
	public static Holder<PlacedFeature> createGrass(String name, Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> configuredFeatureRegistryEntry, int chanceBelow, int chanceAbove) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				NoiseThresholdCountPlacement.of(-0.8, chanceBelow, chanceAbove),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
				BiomeFilter.biome()
		);
	}
	public static Holder<PlacedFeature> createForgetMeNot(String name, Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> configuredFeatureRegistryEntry, int chance) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				RarityFilter.onAverageOnceEvery(chance),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BiomeFilter.biome()
		);
	}
}
