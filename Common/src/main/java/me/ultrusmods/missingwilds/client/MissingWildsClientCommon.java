package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.minecraft.client.renderer.RenderType;

public class MissingWildsClientCommon {
    public static void init() {
        Services.PLATFORM.setBlockRenderType(RenderType.cutoutMipped(),
                MissingWildsBlocks.FALLEN_BIRCH_LOG.get(),
                MissingWildsBlocks.FALLEN_ACACIA_LOG.get(),
                MissingWildsBlocks.FALLEN_OAK_LOG.get(),
                MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(),
                MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(),
                MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(),
                MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(),
                MissingWildsBlocks.FALLEN_WARPED_STEM.get(),
                MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get(),
                MissingWildsBlocks.FALLEN_MANGROVE_LOG.get(),
                MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM.get(),
                MissingWildsBlocks.JAR_BLOCK.get(),
                MissingWildsBlocks.FIREFLY_JAR_BLOCK.get()
        );
        Services.PLATFORM.setBlockRenderType(RenderType.cutout(),
                MissingWildsBlocks.BLUE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.PURPLE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.PINK_FORGET_ME_NOT.get(),
                MissingWildsBlocks.WHITE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.SWEETSPIRE.get(),
                MissingWildsBlocks.POTTED_SWEETSPIRE.get(),
                MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT.get(),
                MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT.get(),
                MissingWildsBlocks.WATERLILY_BLOCK.get());
        Services.PLATFORM.setBlockRenderType(RenderType.translucent(),
                MissingWildsBlocks.TINTED_JAR_BLOCK.get(),
                MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(),
                MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get());
    }
}
