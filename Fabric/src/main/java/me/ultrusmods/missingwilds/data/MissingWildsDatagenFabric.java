package me.ultrusmods.missingwilds.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MissingWildsDatagenFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        if (System.getProperty("mw.common") != null) {
            // Commmon data.
            FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
            pack.addProvider(MissingWildsModelProvider::new);
            pack.addProvider(MissingWildsLootTableProvider::new);
            pack.addProvider(MissingWildsRecipeProvider::new);
            pack.addProvider(MissingWildsTagProvider::new);
        } else {
            // Fabric only data gen here, when I add some.
        }
    }
}
