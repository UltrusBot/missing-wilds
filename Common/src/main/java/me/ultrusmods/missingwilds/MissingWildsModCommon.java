package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.mixin.FireBlockAccessor;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MissingWildsModCommon {

    public static final TagKey<Block> FALLEN_LOGS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Constants.MOD_ID, "fallen_logs"));
    public static void init() {
        MissingWildsBlocks.init();
        MissingWildsItems.init();
        MissingWildsFeatures.init();

        if (!Services.PLATFORM.getPlatformName().equals("Forge")) {
            MissingWildsConfiguredFeatures.init();
            MissingWildsPlacedFeatures.init();
            postInit();
        }
        Constants.LOG.info("Missing Wilds for {} is loading!", Services.PLATFORM.getPlatformName());

    }
    public static void postInit() {
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_OAK_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), 5, 5);

    }
}