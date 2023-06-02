package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ForgeModCompatHandler;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.RegistryObject;
import me.ultrusmods.missingwilds.resource.MissingWildsForgeResources;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {
    public static final List<RegistryObject<Block>> COMPAT_LOGS = new ArrayList<>();
    public MissingWildsForge() {
        if (Services.PLATFORM.isModLoaded("brrp_v1")) {
            ForgeModCompatHandler.loadModCompat();
            MissingWildsForgeResources.registerPack();
        }
        MissingWildsModCommon.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::spawnPlacementsEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerAttributes);

        if (Services.PLATFORM.isModLoaded("brrp_v1")) {
            MissingWildsForgeResources.generatePack();
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MissingWildsModCommon::postInit);
    }


    private void spawnPlacementsEvent(SpawnPlacementRegisterEvent event) {
        event.register(MissingWildsEntities.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarm.createAttributes().build());
    }

}