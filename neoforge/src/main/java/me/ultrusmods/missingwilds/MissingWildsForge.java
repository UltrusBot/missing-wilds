package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.*;
import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {
    public MissingWildsForge(IEventBus eventBus) {
        MissingWildsModCommon.init();
        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::spawnPlacementsEvent);
        eventBus.addListener(this::registerAttributes);
        eventBus.addListener(this::registerItemGroups);
    }


    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MissingWildsModCommon::postInit);
    }


    private void registerItemGroups(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, creativeModeTabRegisterHelper -> {
            creativeModeTabRegisterHelper.register(Constants.id("items"), CreativeModeTab.builder()
                            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG))
                            .title(Component.translatable("itemGroup.missingwilds.items"))
                            .displayItems(Services.PLATFORM::registerItems)
                            .build());
        });
        event.register(Registries.BLOCK, blockRegisterHelper -> MissingWildsBlocks.init(blockRegisterHelper::register));
        event.register(Registries.ITEM, itemRegisterHelper -> MissingWildsItems.init(itemRegisterHelper::register));
        event.register(Registries.FEATURE, featureRegisterHelper -> MissingWildsFeatures.registerFeature(featureRegisterHelper::register));
        event.register(Registries.TREE_DECORATOR_TYPE, treeDecoratorTypeRegisterHelper -> MissingWildsFeatures.registerTreeDecorator(treeDecoratorTypeRegisterHelper::register));
        event.register(Registries.CUSTOM_STAT, customStatRegisterHelper -> MissingWildsStats.init(customStatRegisterHelper::register));
        event.register(Registries.SOUND_EVENT, soundEventRegisterHelper -> MissingWildsSounds.register(soundEventRegisterHelper::register));
        event.register(Registries.PARTICLE_TYPE, particleTypeRegisterHelper -> MissingWildsParticles.register(particleTypeRegisterHelper::register));
        event.register(Registries.BLOCK_ENTITY_TYPE, blockEntityTypeRegisterHelper -> MissingWildsBlockEntities.register(blockEntityTypeRegisterHelper::register));
        event.register(Registries.ENTITY_TYPE, entityTypeRegisterHelper -> MissingWildsEntities.register(entityTypeRegisterHelper::register));
    }
    private void spawnPlacementsEvent(RegisterSpawnPlacementsEvent event) {
        event.register(MissingWildsEntities.FIREFLY_SWARM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MissingWildsEntities.FIREFLY_SWARM, FireflySwarm.createAttributes().build());
    }

}