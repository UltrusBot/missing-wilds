package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.mixin.FireBlockAccessor;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.*;
import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import net.minecraft.world.level.block.Blocks;

public class MissingWildsModCommon {

    public static void init() {
        MissingWildsBlocks.init();
        Services.PLATFORM.duringBlockRegistering();
        MissingWildsItems.init();
        Services.PLATFORM.duringItemRegistering();
        MissingWildsFeatures.init();
        MissingWildsSounds.init();
        MissingWildsParticles.init();
        MissingWildsBlockEntities.init();
        if (!Services.PLATFORM.getPlatformName().equals("Forge")) {
            postInit();
        }
        Constants.LOG.info("Missing Wilds for {} is loading!", Services.PLATFORM.getPlatformName());

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