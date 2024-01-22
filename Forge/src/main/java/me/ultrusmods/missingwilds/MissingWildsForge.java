package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ForgeModCompatHandler;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.RegistryObject;
import me.ultrusmods.missingwilds.resource.MissingWildsForgeAssetResources;
import me.ultrusmods.missingwilds.resource.MissingWildsForgeDataResources;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {
    public static final List<RegistryObject<Block>> COMPAT_LOGS = new ArrayList<>();
    public static final ForgeModCompatHandler FORGE_MOD_COMPAT_HANDLER = new ForgeModCompatHandler();
    public MissingWildsForge() {
        MissingWildsModCommon.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MissingWildsForge::checkAndRunAssetGenerator);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::spawnPlacementsEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerAttributes);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerItemGroups);
        if (Services.PLATFORM.isModLoaded("dynamic_asset_generator")) {
            MissingWildsForgeDataResources.init();
        }
    }

    public static void checkAndRunAssetGenerator() {
        if (Services.PLATFORM.isModLoaded("dynamic_asset_generator")) {
            MissingWildsForgeAssetResources.init();
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MissingWildsModCommon::postInit);
    }


    private void registerItemGroups(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, creativeModeTabRegisterHelper -> {
            creativeModeTabRegisterHelper.register(Constants.id("items"), CreativeModeTab.builder()
                            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
                            .title(Component.translatable("itemGroup.missingwilds.items"))
                            .displayItems(Services.PLATFORM::registerItems)
                            .build());
        });
    }
    private void spawnPlacementsEvent(SpawnPlacementRegisterEvent event) {
        event.register(MissingWildsEntities.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarm.createAttributes().build());
    }

}