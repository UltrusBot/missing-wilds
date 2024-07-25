package me.ultrusmods.missingwilds.compat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.lukebemish.defaultresources.api.GlobalResourceManager;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.data.JarData;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.data.ModCompatJsonData;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ModCompatHandler {

    private static final GlobalResourceManager DATA = GlobalResourceManager.getGlobalData().prefix(Constants.MOD_ID);
    private static final Gson GSON = new Gson();

    /**
     * This is the list of all enabled mod compats.
     */
    public static final List<ModCompatInstance> enabledModCompats = new ArrayList<>();


    /**
     * List of fallen log items added by mod compats.
     */
    public static final Map<LogData, Item> FALLEN_LOG_ITEMS = new HashMap<>();
    /**
     * List of fallen log blocks added by mod compats.
     */
    public static final Map<ResourceLocation, Block> FALLEN_LOG_BLOCKS = new HashMap<>();

    /**
     * Other items besides fallen logs that should be added to the missing wilds item group.
     */
    public static final List<Item> OTHER_ITEMS_TO_ITEM_GROUPS = new ArrayList<>();

    public static final Map<JarData, Block> JAR_BLOCKS = new HashMap<>();
    public static final Map<JarData, Block> FIREFLY_JAR_BLOCKS = new HashMap<>();
    public static final Map<JarData, Block> FOOD_JAR_BLOCKS = new HashMap<>();


    /**
     * Loads mod compat, typically from json files, and loads them into the mod compat list.
     * This is before the mod compat is actually added.
     */
    public static void loadModCompat() {
        Map<ResourceLocation, List<Resource>> resourceLocations = DATA.listResourceStacks("compat", resourceLocation -> resourceLocation.getPath().endsWith(".json"));
        resourceLocations.forEach((resourceLocation, resources) -> resources.forEach(resource -> {
            try {
                Reader reader = resource.openAsReader();
                JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
                var data = ModCompatJsonData.CODEC.parse(JsonOps.INSTANCE, jsonObject);
                if (data.result().isPresent()) {
                    ModCompatJsonData modCompatJsonData = data.getOrThrow();
                    if (Services.PLATFORM.isModLoaded(modCompatJsonData.modid())) {
                        if (getModCompats().stream().anyMatch(modCompatInstance -> modCompatInstance.getModid().equals(modCompatJsonData.modid()))) {
                            // Should catch weird duplicate loading issue.
                            return;
                        }
                        addModCompat(new JsonDefinedModCompatInstance(modCompatJsonData));
                    }
                } else {
                    Constants.LOG.error("Failed to parse mod compat file {} with error {}", resourceLocation, data.error().get().message());
                }

            } catch (IOException e) {
                Constants.LOG.error("Failed to read mod compat file {} with error {}", resourceLocation, e.getMessage());
            }
        }));
    }

    public static void init() {
        if (isJsonModCompatEnabled()) {
            Constants.LOG.info("Attempting to load mod compats.");
            loadModCompat();
        }
    }

    public static boolean isJsonModCompatEnabled() {
        return Services.PLATFORM.isModLoaded("dynamic_asset_generator");
    }

    public static List<ModCompatInstance> getModCompats() {
        return enabledModCompats;
    }

    public static void addModCompat(ModCompatInstance modCompat) {
        enabledModCompats.add(modCompat);
    }

    public static void addFallenLogItem(Item item, LogData logData) {
        FALLEN_LOG_ITEMS.put(logData, item);
    }
    public static void addFallenLogBlock(ResourceLocation data,Block block) {
        FALLEN_LOG_BLOCKS.put(data, block);
    }
    public static Map<LogData, Item> getFallenLogItems() {
        return FALLEN_LOG_ITEMS;
    }
    public static List<Block> getFallenLogBlocks() {
        return FALLEN_LOG_BLOCKS.values().stream().toList();
    }

    public static void addJarBlock(JarData jarData, Block block) {
        JAR_BLOCKS.put(jarData, block);
    }
    public static void addFoodJarBlock(JarData jarData, Block block) {
        FOOD_JAR_BLOCKS.put(jarData, block);
    }
    public static void addFireflyJarBlock(JarData jarData, Block block) {
        FIREFLY_JAR_BLOCKS.put(jarData, block);
    }
    public static Map<JarData, Block> getJarBlocks() {
        return JAR_BLOCKS;
    }
    public static Map<JarData, Block> getFoodJarBlocks() {
        return FOOD_JAR_BLOCKS;
    }
    public static Map<JarData, Block> getFireflyJarBlocks() {
        return FIREFLY_JAR_BLOCKS;
    }

    public static void addItemToItemGroup(Item item) {
        OTHER_ITEMS_TO_ITEM_GROUPS.add(item);
    }
}
