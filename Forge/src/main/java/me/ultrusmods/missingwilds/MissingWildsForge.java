package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompatForge;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {
    public static final List<RegistryObject<Block>> COMPAT_LOGS = new ArrayList<>();
    public MissingWildsForge() {
        MissingWildsModCommon.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        if (Services.PLATFORM.isModLoaded("better_runtime_resource_pack")) {
            ModCompatForge.checkModCompat();
        }


    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MissingWildsModCommon::postInit);
    }


}