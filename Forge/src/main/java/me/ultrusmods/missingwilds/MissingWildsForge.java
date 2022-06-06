package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
}