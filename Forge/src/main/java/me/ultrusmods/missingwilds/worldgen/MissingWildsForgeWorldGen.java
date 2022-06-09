package me.ultrusmods.missingwilds.worldgen;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class MissingWildsForgeWorldGen {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void removeBirchTrees(BiomeLoadingEvent biomeLoadingEvent) {
        if (biomeLoadingEvent.getName().equals(Biomes.BIRCH_FOREST.location()) || biomeLoadingEvent.getName().equals(Biomes.OLD_GROWTH_BIRCH_FOREST.location())) {
            List<Holder<PlacedFeature>> features = biomeLoadingEvent.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            for (int i = features.size() - 1; i >= 0; i--) {
                Holder<PlacedFeature> placedFeatureHolder = features.get(i);
                if (placedFeatureHolder.is(VegetationPlacements.TREES_BIRCH.unwrapKey().get())) {
                    features.remove(i);
                }
                if (placedFeatureHolder.is(VegetationPlacements.BIRCH_TALL.unwrapKey().get())) {
                    features.remove(i);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addNewBirchFeatures(BiomeLoadingEvent biomeLoadingEvent) {
        boolean isBirch = biomeLoadingEvent.getName().equals(Biomes.BIRCH_FOREST.location());
        boolean isTallBirch = biomeLoadingEvent.getName().equals(Biomes.OLD_GROWTH_BIRCH_FOREST.location());
        if (isBirch || isTallBirch) {
            List<Holder<PlacedFeature>> features = biomeLoadingEvent.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            if (isBirch) {
                features.add(MissingWildsPlacedFeatures.TREES_BIRCH);
            }
            if (isTallBirch) {
                features.add( MissingWildsPlacedFeatures.TALL_TREES_BIRCH);
            }
            features.add(MissingWildsPlacedFeatures.PLACED_FALLEN_BIRCH_LOG);
            features.add(MissingWildsPlacedFeatures.BLUE_FORGET_ME_NOT);
            features.add(MissingWildsPlacedFeatures.PURPLE_FORGET_ME_NOT);
            features.add(MissingWildsPlacedFeatures.PINK_FORGET_ME_NOT);
            features.add(MissingWildsPlacedFeatures.WHITE_FORGET_ME_NOT);
            features.add(MissingWildsPlacedFeatures.SWEETSPIRE);
            features.add(MissingWildsPlacedFeatures.TALL_GRASS);
            features.add(MissingWildsPlacedFeatures.GRASS);
        }
    }
}
