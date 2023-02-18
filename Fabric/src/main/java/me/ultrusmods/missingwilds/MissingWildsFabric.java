package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsFabric implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static final CreativeModeTab MISSING_WILD_ITEMS = FabricItemGroupBuilder.create(
                    new ResourceLocation(Constants.MOD_ID, "items"))
            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
            .build();

    @Override
    public void onInitialize() {
        MissingWildsResources.init();
        MissingWildsModCommon.init();
        ModCompat.checkModCompat();
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
