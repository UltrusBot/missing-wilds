package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsFabric implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static CreativeModeTab MISSING_WILD_ITEMS;

    @Override
    public void onInitialize() {
        if (Services.PLATFORM.isModLoaded("advanced_runtime_resource_pack")) {
            MissingWildsResources.init();
        }
        MissingWildsModCommon.init();
        if (Services.PLATFORM.isModLoaded("advanced_runtime_resource_pack")) {
            ModCompat.checkModCompat();
        }
        MISSING_WILD_ITEMS = FabricItemGroup.builder(
                        Constants.id("items"))
                .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
                .displayItems((displayParameters, output) -> {
                    Services.PLATFORM.registerItems(displayParameters, output);
                    ModCompat.FALLEN_LOG_ITEMS.forEach(output::accept);
                })
                .build();
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
