package me.ultrusmods.missingwilds;

import com.google.common.collect.HashBiMap;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.minecraft.world.level.block.Block;

public class JarMaps {
    public static HashBiMap<Block, Block> JAR_TO_FIREFLY_JAR = HashBiMap.create();


    static {
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.JAR_BLOCK.get(), MissingWildsBlocks.FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.TINTED_JAR_BLOCK.get(), MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get());
        JAR_TO_FIREFLY_JAR.put(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(), MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get());
    }

}
