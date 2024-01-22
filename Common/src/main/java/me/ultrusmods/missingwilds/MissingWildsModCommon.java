package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.mixin.FireBlockAccessor;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.*;
import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import net.minecraft.world.level.block.Blocks;

public class MissingWildsModCommon {

    public static void init() {
        Services.PLATFORM.getModCompatHandler().init();
        if (!Services.PLATFORM.getModCompatHandler().getModCompats().isEmpty()) {
            Constants.LOG.info("Enabled missing wilds compatibility for mods: " + Services.PLATFORM.getModCompatHandler().enabledModCompats.stream().map(ModCompatInstance::getModid).toList());
        }
        MissingWildsBlocks.init();
        Services.PLATFORM.duringBlockRegistering();
        MissingWildsItems.init();
        Services.PLATFORM.duringItemRegistering();
        MissingWildsFeatures.init();
        MissingWildsSounds.init();
        MissingWildsParticles.init();
        MissingWildsBlockEntities.init();
        MissingWildsEntities.init();
        if (!Services.PLATFORM.getPlatformName().equals("Forge")) {
            postInit();
        }
        Constants.LOG.info("Missing Wilds for {} is loading!", Services.PLATFORM.getPlatformName());
        ColorSets.addSpecialColors(); // Loads special firefly jar colors

    }
    public static void postInit() {
        MissingWildsStats.init();
        MissingWildsConfiguredFeatures.init();
        MissingWildsPlacedFeatures.init();
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_OAK_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), 5, 5);
    }
}