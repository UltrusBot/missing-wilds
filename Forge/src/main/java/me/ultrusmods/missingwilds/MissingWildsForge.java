package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {

    public MissingWildsForge() {
        MissingWildsModCommon.init();
        MissingWildsWorldGen.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        MissingWildsModCommon.postInit();
        event.enqueueWork(() -> {
            MissingWildsConfiguredFeatures.init();
            MissingWildsPlacedFeatures.init();
        });
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void removeBirchTrees(BiomeLoadingEvent biomeLoadingEvent) {
        if (biomeLoadingEvent.getName() == Biomes.BIRCH_FOREST.getRegistryName() || biomeLoadingEvent.getName() == Biomes.OLD_GROWTH_BIRCH_FOREST.getRegistryName()) {
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
    public void addNewBirchFeatures(BiomeLoadingEvent biomeLoadingEvent) {
        if (biomeLoadingEvent.getName() == null) return;
        boolean isBirch = biomeLoadingEvent.getName().equals(Biomes.BIRCH_FOREST.getRegistryName()) ;
        boolean isTallBirch = biomeLoadingEvent.getName().equals(Biomes.OLD_GROWTH_BIRCH_FOREST.getRegistryName());
        if (isBirch || isTallBirch) {
            List<Holder<PlacedFeature>> features = biomeLoadingEvent.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            if (isBirch) {
                features.add(MissingWildsPlacedFeatures.TREES_BIRCH);
            }
            if (isTallBirch) {
                features.add(MissingWildsPlacedFeatures.TALL_TREES_BIRCH);
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