package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.FabricModCompatHandler;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsFabricResources;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;

public class MissingWildsFabric implements ModInitializer {
    public static CreativeModeTab MISSING_WILD_ITEMS;
    public static final ModCompatHandler FABRIC_MOD_COMPAT_HANDLER = new FabricModCompatHandler();

    @Override
    public void onInitialize() {
        MissingWildsModCommon.init();
        if (Services.PLATFORM.isModLoaded("advanced_runtime_resource_pack")) {
            MissingWildsFabricResources.init();
        }
        MISSING_WILD_ITEMS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, "items"), FabricItemGroup.builder()
                .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
                .title(Component.translatable("itemGroup.missingwilds.items"))
                .displayItems(Services.PLATFORM::registerItems)
                .build());
        MissingWildsWorldGen.init();
        SpawnPlacements.register(MissingWildsEntities.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules);
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(MissingWildsTags.SPAWNS_FIREFLY_SWARMS),
                MobCategory.AMBIENT,
                MissingWildsEntities.FIREFLY_SWARM.get(),
                12,
                1,
                2
        );
        FabricDefaultAttributeRegistry.register(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarm.createAttributes().build());
    }
}
