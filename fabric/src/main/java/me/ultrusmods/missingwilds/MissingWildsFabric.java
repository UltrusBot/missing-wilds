package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.template.TemplateModCompat;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.*;
import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;

public class MissingWildsFabric implements ModInitializer {
    public static CreativeModeTab MISSING_WILD_ITEMS;
    @Override
    public void onInitialize() {
        MissingWildsModCommon.init();
        if (Services.PLATFORM.isModLoaded("templates")) {
            ModCompatHandler.addModCompat(new TemplateModCompat());
        }
        MissingWildsBlocks.init((resourceLocation, block) -> Registry.register(BuiltInRegistries.BLOCK, resourceLocation, block));
        MissingWildsItems.init((resourceLocation, item) -> Registry.register(BuiltInRegistries.ITEM, resourceLocation, item));
        MissingWildsSounds.register((resourceLocation, soundEvent) -> Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, soundEvent));

        MissingWildsFeatures.registerFeature((resourceLocation, feature) -> Registry.register(BuiltInRegistries.FEATURE, resourceLocation, feature));
        MissingWildsFeatures.registerTreeDecorator((resourceLocation, treeDecoratorType) -> Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, resourceLocation, treeDecoratorType));

        MissingWildsStats.init((id, loc) -> Registry.register(BuiltInRegistries.CUSTOM_STAT, id, loc));
        MissingWildsParticles.register((resourceLocation, particleType) -> Registry.register(BuiltInRegistries.PARTICLE_TYPE, resourceLocation, particleType));
        MissingWildsBlockEntities.register((resourceLocation, blockEntityType) -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, resourceLocation, blockEntityType));
        MissingWildsEntities.register((resourceLocation, entityType) -> Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceLocation, entityType));

        MISSING_WILD_ITEMS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.id("items"), FabricItemGroup.builder()
                .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG))
                .title(Component.translatable("itemGroup.missingwilds.items"))
                .displayItems(Services.PLATFORM::registerItems)
                .build());
        MissingWildsWorldGen.init();
        SpawnPlacements.register(MissingWildsEntities.FIREFLY_SWARM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules);
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(MissingWildsTags.SPAWNS_FIREFLY_SWARMS),
                MobCategory.AMBIENT,
                MissingWildsEntities.FIREFLY_SWARM,
                20,
                1,
                2
        );
        FabricDefaultAttributeRegistry.register(MissingWildsEntities.FIREFLY_SWARM, FireflySwarm.createAttributes().build());
        MissingWildsModCommon.postInit();

    }
}
