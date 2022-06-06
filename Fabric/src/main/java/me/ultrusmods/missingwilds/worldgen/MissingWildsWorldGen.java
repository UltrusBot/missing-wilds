package me.ultrusmods.missingwilds.worldgen;

import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.function.Predicate;

import static me.ultrusmods.missingwilds.Constants.MOD_ID;

public class MissingWildsWorldGen {
    public static final Predicate<BiomeSelectionContext> BIOMES = BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
    public static void init() {
        BiomeModification biomeModifications = BiomeModifications.create(new ResourceLocation(MOD_ID, "world_features"));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PLACED_FALLEN_BIRCH_LOG.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.BLUE_FORGET_ME_NOT.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PURPLE_FORGET_ME_NOT.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PINK_FORGET_ME_NOT.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.WHITE_FORGET_ME_NOT.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.SWEETSPIRE.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.TALL_GRASS.value()));
        biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MissingWildsPlacedFeatures.GRASS.value()));
        BiomeModifications.create(new ResourceLocation(MOD_ID, "branched_birch_tree"))
                .add(ModificationPhase.REPLACEMENTS,
                        BIOMES,
                        (context) -> {
                            if (context.getGenerationSettings().removeBuiltInFeature(VegetationPlacements.TREES_BIRCH.value())) {
                                context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BuiltInRegistryKeys.get(MissingWildsPlacedFeatures.TREES_BIRCH.value()));
                            }
                            if (context.getGenerationSettings().removeBuiltInFeature(VegetationPlacements.BIRCH_TALL.value())) {
                                context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BuiltInRegistryKeys.get(MissingWildsPlacedFeatures.TALL_TREES_BIRCH.value()));
                            }
                        });
    }
}
