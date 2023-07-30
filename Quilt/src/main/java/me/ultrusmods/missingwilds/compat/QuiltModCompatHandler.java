package me.ultrusmods.missingwilds.compat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.lukebemish.defaultresources.api.ResourceProvider;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsQuilt;
import me.ultrusmods.missingwilds.compat.template.TemplateModCompat;
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

public class QuiltModCompatHandler {

    public static final List<Item> FALLEN_LOG_ITEMS = new ArrayList<>();
    public static HashMap<String, ModCompat> modCompats = new HashMap<>();
    private static final Gson GSON = new Gson();


    public static void checkModCompat() {
        QuiltModCompatHandler.loadModCompat();
        modCompats.values().forEach((modCompat) -> {
            modCompat.logs().forEach(logEither -> {
                LogData logData = logEither.left().isPresent() ? QuiltModCompatHandler.getSimpleLogName(logEither.left().get(), modCompat.modid()) : logEither.right().get();
                RegistryObject<Block> block = MissingWildsBlocks.registerFallenLog(modCompat.modid() + "_" + logData.name());
                MissingWildsQuilt.COMPAT_LOGS.add(block.get());
                Item item = MissingWildsItems.register(modCompat.modid() + "_" + logData.name(), block).get();
                FALLEN_LOG_ITEMS.add(item);
            });
        });
        if (Services.PLATFORM.isModLoaded("templates")) {
            TemplateModCompat.init();
        }
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
