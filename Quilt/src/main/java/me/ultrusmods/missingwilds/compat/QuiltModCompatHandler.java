package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.MissingWildsQuilt;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.data.ModCompat;
import me.ultrusmods.missingwilds.data.ModCompatLoader;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

/**
 * Handles the mod compat files loaded by {@link ModCompatLoader}
 */
public class QuiltModCompatHandler {

    public static void checkModCompat() {
        ModCompatLoader.init();
        ModCompatLoader.modCompats.values().forEach((modCompat) -> {
            modCompat.logs().forEach(logEither -> {
                LogData logData = logEither.left().isPresent() ? QuiltModCompatHandler.getSimpleLogName(logEither.left().get(), modCompat.modid()) : logEither.right().get();
                RegistryObject<Block> block = MissingWildsBlocks.registerFallenLog(modCompat.modid() + "_" + logData.name());
                MissingWildsQuilt.COMPAT_LOGS.add(block.get());
                MissingWildsItems.register(modCompat.modid() + "_" + logData.name(), block).get();
            });
        });
    }

    /**
     * Gets the LogData of a log name, that follows the vanilla texture location and naming convention.
     *
     * @param logName The name of the log. (e.g. oak_log)
     * @param modId   The id of the mod.
     * @return The LogData of the log.
     */
    public static LogData getSimpleLogName(String logName, String modId) {
        return new LogData("fallen_" + logName, modId + ":" + logName, modId + ":block/" + logName, modId + ":block/stripped_" + logName);
    }
}
