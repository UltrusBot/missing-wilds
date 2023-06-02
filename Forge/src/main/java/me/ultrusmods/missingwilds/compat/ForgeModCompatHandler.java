package me.ultrusmods.missingwilds.compat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import io.github.lukebemish.defaultresources.api.ResourceProvider;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsForge;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.data.ModCompat;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForgeModCompatHandler {
    public static HashMap<String, ModCompat> modCompats = new HashMap<>();
    private static final Gson GSON = new Gson();

    private final List<LogData> logList = new ArrayList<>();
    private static final HashMap<String, RegistryObject<Item>> itemMap = new HashMap<>();
    private static final HashMap<String, RegistryObject<Block>> blockMap = new HashMap<>();

    public ForgeModCompatHandler() {
    }

    public static void checkModCompat() {
        loadModCompat();
    }
    public static void loadModCompat() {
        ResourceProvider.forceInitialization();

        var resourceLocations = ResourceProvider.instance().getResources(Constants.MOD_ID, "compat", predicate -> true);

        for (ResourceLocation resource : resourceLocations) {
            try (var resourceStream = ResourceProvider.instance().getResourceStreams(Constants.MOD_ID, resource)) {
                var optional = resourceStream.findFirst();
                if (optional.isPresent() && resource.getPath().endsWith(".json")) {
                    InputStream stream = optional.get();
                    JsonObject jsonObject = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
                    ModCompat modCompat = ModCompat.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow(false, Constants.LOG::error);
                    if (Services.PLATFORM.isModLoaded(modCompat.modid())) {
                        modCompats.put(modCompat.modid(), modCompat);
                    }
                }
            } catch (Exception e) {
                Constants.LOG.error("Failed to load mod compat file {} with error {}", resource, e.getMessage());
            }
        }
    }

    public static void registerModCompatBlocks() {
        modCompats.values().forEach((modCompat) -> {
            modCompat.logs().forEach(logEither -> {
                LogData logData = logEither.left().isPresent() ? ForgeModCompatHandler.getSimpleLogName(logEither.left().get(), modCompat.modid()) : logEither.right().get();
                addLogBlock(logData, modCompat.modid());
            });
        });
    }

    public static void registerModCompatItems() {
        modCompats.values().forEach((modCompat) -> {
            modCompat.logs().forEach(logEither -> {
                LogData logData = logEither.left().isPresent() ? ForgeModCompatHandler.getSimpleLogName(logEither.left().get(), modCompat.modid()) : logEither.right().get();
                addLogItem(logData, modCompat.modid());
            });
        });
    }

    public static void addLogItem(LogData logData, String modid) {
        RegistryObject<Item> item = MissingWildsItems.register(modid + "_" + logData.name(), ForgeModCompatHandler.getLogsBlocks().get(logData.blockId()));
        ForgeModCompatHandler.addItem(item, logData.blockId());

    }

    public static void addLogBlock(LogData logData, String modid) {
        RegistryObject<Block> block = MissingWildsBlocks.registerFallenLog(modid + "_" + logData.name());
        ForgeModCompatHandler.addBlock(block, logData.blockId());
        MissingWildsForge.COMPAT_LOGS.add(block);
    }

    public List<LogData> getLogList() {
        return logList;
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

    public static HashMap<String, RegistryObject<Block>> getLogsBlocks() {
        return blockMap;
    }

    public static HashMap<String, RegistryObject<Item>> getLogItems() {
        return itemMap;
    }

    public static void addBlock(RegistryObject<Block> block, String blockId) {
        blockMap.put(blockId, block);
    }

    public static void addItem(RegistryObject<Item> item, String itemId) {
        itemMap.put(itemId, item);
    }
}
