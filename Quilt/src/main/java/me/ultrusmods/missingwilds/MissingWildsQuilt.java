package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompatQuilt;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsQuiltResources;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsQuilt implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static final CreativeModeTab MISSING_WILD_ITEMS = QuiltItemGroup.builder(
                    new ResourceLocation(Constants.MOD_ID, "items"))
            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
            .build();

    @Override
    public void onInitialize(ModContainer mod) {
        //        MissingWildsResources.init();
        MissingWildsModCommon.init();
        ModCompatQuilt.checkModCompat();
        MissingWildsWorldGen.init();
        MissingWildsQuiltResources.init();
        SpawnPlacements.register(MissingWildsEntities.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflySwarm::checkFireflySpawnRules);
        FabricDefaultAttributeRegistry.register(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarm.createAttributes().build());

    }
}
