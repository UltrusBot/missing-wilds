package me.ultrusmods.missingwilds.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MissingWildsDatagenFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        if (System.getProperty("mw.common") != null) {
            // Commmon data.
            fabricDataGenerator.addProvider(MissingWildsModelProvider::new);
            fabricDataGenerator.addProvider(MissingWildsLootTableProvider::new);
            fabricDataGenerator.addProvider(MissingWildsRecipeProvider::new);
        } else {
            // Fabric only data gen here, when I add some.
        }
    }
}
