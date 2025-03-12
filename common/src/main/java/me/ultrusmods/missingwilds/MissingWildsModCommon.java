package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.bovines.BovinesAndButtercupsModCompat;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.mixin.FireBlockAccessor;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import me.ultrusmods.missingwilds.resource.MissingWildsDataResources;
import net.minecraft.world.level.block.Blocks;

public class MissingWildsModCommon {

    public static void init() {
        ModCompatHandler.init();
        if (Services.PLATFORM.isModLoaded("bovinesandbuttercups") && ModCompatHandler.isJsonModCompatEnabled()) {
            ModCompatHandler.addModCompat(new BovinesAndButtercupsModCompat());
        }

        Constants.LOG.info("Missing Wilds for {} is loading!", Services.PLATFORM.getPlatformName());
        ColorSets.addSpecialColors(); // Loads special firefly jar colors

    }
    public static void postInit() {
        MissingWildsConfiguredFeatures.init();
        MissingWildsPlacedFeatures.init();
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_BIRCH_LOG, 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_OAK_LOG, 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_JUNGLE_LOG, 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_SPRUCE_LOG, 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_DARK_OAK_LOG, 5, 5);
        ((FireBlockAccessor) Blocks.FIRE).registerFlameable$MissingWilds(MissingWildsBlocks.FALLEN_ACACIA_LOG, 5, 5);
        if (!ModCompatHandler.getModCompats().isEmpty()) {
            Constants.LOG.info("Enabled missing wilds compatibility for mods: {}", ModCompatHandler.enabledModCompats.stream().map(ModCompatInstance::getModid).toList());
        }
        if (ModCompatHandler.isJsonModCompatEnabled()) {
            MissingWildsDataResources.init();
        }
    }
}