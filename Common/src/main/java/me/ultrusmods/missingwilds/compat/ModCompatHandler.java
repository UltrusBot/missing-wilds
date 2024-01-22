package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.data.JarData;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ModCompatHandler {

    /**
     * This is the list of all enabled mod compats.
     */
    public List<ModCompatInstance> enabledModCompats = new ArrayList<>();


    /**
     * List of fallen log items added by mod compats.
     */
    public final Map<LogData, RegistryObject<Item>> FALLEN_LOG_ITEMS = new HashMap<>();
    /**
     * List of fallen log blocks added by mod compats.
     */
    public final List<RegistryObject<Block>> FALLEN_LOG_BLOCKS = new ArrayList<>();

    /**
     * Other items besides fallen logs that should be added to the missing wilds item group.
     */
    public final List<RegistryObject<Item>> OTHER_ITEMS_TO_ITEM_GROUPS = new ArrayList<>();

    public final Map<JarData, RegistryObject<Block>> JAR_BLOCKS = new HashMap<>();
    public final Map<JarData, RegistryObject<Block>> FIREFLY_JAR_BLOCKS = new HashMap<>();
    public final Map<JarData, RegistryObject<Block>> FOOD_JAR_BLOCKS = new HashMap<>();


    /**
     * Loads mod compat, typically from json files, and loads them into the mod compat list.
     * This is before the mod compat is actually added.
     */
    public abstract void loadModCompat();

    public void init() {
        if (isJsonModCompatEnabled()) {
            loadModCompat();
        }
    }

    public abstract boolean isJsonModCompatEnabled();

    public List<ModCompatInstance> getModCompats() {
        return enabledModCompats;
    }

    public void addModCompat(ModCompatInstance modCompat) {
        enabledModCompats.add(modCompat);
    }

    public void addFallenLogItem(RegistryObject<Item> item, LogData logData) {
        FALLEN_LOG_ITEMS.put(logData, item);
    }
    public void addFallenLogBlock(RegistryObject<Block> block) {
        FALLEN_LOG_BLOCKS.add(block);
    }
    public Map<LogData, RegistryObject<Item>> getFallenLogItems() {
        return FALLEN_LOG_ITEMS;
    }
    public List<RegistryObject<Block>> getFallenLogBlocks() {
        return FALLEN_LOG_BLOCKS;
    }

    public void addJarBlock(JarData jarData, RegistryObject<Block> block) {
        JAR_BLOCKS.put(jarData, block);
    }
    public void addFoodJarBlock(JarData jarData, RegistryObject<Block> block) {
        FOOD_JAR_BLOCKS.put(jarData, block);
    }
    public void addFireflyJarBlock(JarData jarData, RegistryObject<Block> block) {
        FIREFLY_JAR_BLOCKS.put(jarData, block);
    }
    public Map<JarData, RegistryObject<Block>> getJarBlocks() {
        return JAR_BLOCKS;
    }
    public Map<JarData, RegistryObject<Block>> getFoodJarBlocks() {
        return FOOD_JAR_BLOCKS;
    }
    public Map<JarData, RegistryObject<Block>> getFireflyJarBlocks() {
        return FIREFLY_JAR_BLOCKS;
    }

    public void addItemToItemGroup(RegistryObject<Item> item) {
        OTHER_ITEMS_TO_ITEM_GROUPS.add(item);
    }
}
