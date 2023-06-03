package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.QuiltModCompatHandler;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsQuiltResources;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsQuilt implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static CreativeModeTab MISSING_WILD_ITEMS;

    @Override
    public void onInitialize(ModContainer mod) {
        MissingWildsModCommon.init();
        QuiltModCompatHandler.checkModCompat();
        MISSING_WILD_ITEMS = FabricItemGroup.builder(
                        Constants.id("items"))
                .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
                .displayItems((displayParameters, output) -> {
                    Services.PLATFORM.registerItems(displayParameters, output);
                    QuiltModCompatHandler.FALLEN_LOG_ITEMS.forEach(output::accept);
                })
                .build();
        MissingWildsWorldGen.init();
        MissingWildsQuiltResources.init();
        BiomeModifications.addSpawn(
                BiomeSelectors.isIn(MissingWildsTags.SPAWNS_FIREFLY_SWARMS),
                MobCategory.AMBIENT,
                MissingWildsEntities.FIREFLY_SWARM.get(),
                12,
                1,
                2
        );
        SpawnPlacements.register(MissingWildsEntities.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules);
        FabricDefaultAttributeRegistry.register(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarm.createAttributes().build());

    }
}
