package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.MissingWildsMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class MissingWildsPlacedFeatures {
	public static final RegistryEntry<PlacedFeature> PLACED_FALLEN_BIRCH_LOG = registerPlacedFeature(
			"fallen_birch", MissingWildsConfiguredFeatures.CONFIGURED_FALLEN_BIRCH_LOG,
			PlacedFeatures.wouldSurvive(MissingWildsBlocks.FALLEN_BIRCH_LOG),
			CountPlacementModifier.of(1),
			SquarePlacementModifier.of(),
			PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.of()
	);
	public static final RegistryEntry<PlacedFeature> BLUE_FORGET_ME_NOT = createForgetMeNot("blue_forget_me_not", MissingWildsConfiguredFeatures.BLUE_FORGET_ME_NOT, 20);
	public static final RegistryEntry<PlacedFeature> PURPLE_FORGET_ME_NOT = createForgetMeNot("purple_forget_me_not", MissingWildsConfiguredFeatures.PURPLE_FORGET_ME_NOT, 20);
	public static final RegistryEntry<PlacedFeature> PINK_FORGET_ME_NOT = createForgetMeNot("pink_forget_me_not", MissingWildsConfiguredFeatures.PINK_FORGET_ME_NOT, 20);
	public static final RegistryEntry<PlacedFeature> WHITE_FORGET_ME_NOT = createForgetMeNot("white_forget_me_not", MissingWildsConfiguredFeatures.WHITE_FORGET_ME_NOT, 32);
	public static final RegistryEntry<PlacedFeature> SWEETSPIRE = createRares("sweetspire", MissingWildsConfiguredFeatures.SWEETSPIRE, 10);
	public static final RegistryEntry<PlacedFeature> TALL_GRASS = createRares("tall_grass", MissingWildsConfiguredFeatures.TALL_GRASS, 10);
	public static final RegistryEntry<PlacedFeature> GRASS = createGrass("grass", MissingWildsConfiguredFeatures.GRASS, 5, 10);

	public static final RegistryEntry<PlacedFeature> TREES_BIRCH = registerPlacedFeature(
			"trees_birch", MissingWildsConfiguredFeatures.BIRCH_BEES_0002,
			VegetationPlacedFeatures.modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(15, 0.1f, 3),
					Blocks.BIRCH_SAPLING)
	);
	public static final RegistryEntry<PlacedFeature> TALL_TREES_BIRCH = registerPlacedFeature(
			"tall_trees_birch", MissingWildsConfiguredFeatures.SUPER_BIRCH_BEES_0002,
			VegetationPlacedFeatures.modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(15, 0.1f, 3),
					Blocks.BIRCH_SAPLING)
	);

	public static void init() {
		BiomeModifications.create(MissingWildsMod.id("branched_birch_tree"))
				.add(ModificationPhase.REPLACEMENTS,
						MissingWildsMod.BIOMES,
						(context) -> {
							if (context.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BIRCH.value())) {
								context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TREES_BIRCH.getKey().get());
							}
							if (context.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL.value())) {
								context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TALL_TREES_BIRCH.getKey().get());
							}
						});
	}

	public static RegistryEntry<PlacedFeature> registerPlacedFeature(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
		return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, MissingWildsMod.id(id), new PlacedFeature(RegistryEntry.upcast(configuredFeature), List.copyOf(List.of(modifiers))));
	}

	public static RegistryEntry<PlacedFeature> registerPlacedFeature(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
		return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, MissingWildsMod.id(id), new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
	}
	public static RegistryEntry<PlacedFeature> createForgetMeNot(String name, RegistryEntry<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntry, int chance) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				NoiseThresholdCountPlacementModifier.of(-0.8, 0, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BiomePlacementModifier.of()
		);
	}
	public static RegistryEntry<PlacedFeature> createRares(String name, RegistryEntry<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntry, int chance) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				RarityFilterPlacementModifier.of(chance),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BiomePlacementModifier.of()
		);
	}
	public static RegistryEntry<PlacedFeature> createGrass(String name, RegistryEntry<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntry, int chanceBelow, int chanceAbove) {
		return registerPlacedFeature(
				name,
				configuredFeatureRegistryEntry,
				NoiseThresholdCountPlacementModifier.of(-0.8, chanceBelow, chanceAbove),
				SquarePlacementModifier.of(),
				PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
				BiomePlacementModifier.of()
		);
	}
}
