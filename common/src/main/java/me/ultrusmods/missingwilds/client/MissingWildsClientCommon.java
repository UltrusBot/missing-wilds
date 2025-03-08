package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.client.render.FoodJarRenderer;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MissingWildsClientCommon {
    public static void init() {
        Services.PLATFORM.setBlockRenderType(RenderType.cutoutMipped(),
                MissingWildsBlocks.FALLEN_BIRCH_LOG,
                MissingWildsBlocks.FALLEN_ACACIA_LOG,
                MissingWildsBlocks.FALLEN_OAK_LOG,
                MissingWildsBlocks.FALLEN_SPRUCE_LOG,
                MissingWildsBlocks.FALLEN_DARK_OAK_LOG,
                MissingWildsBlocks.FALLEN_JUNGLE_LOG,
                MissingWildsBlocks.FALLEN_CRIMSON_STEM,
                MissingWildsBlocks.FALLEN_WARPED_STEM,
                MissingWildsBlocks.FALLEN_MUSHROOM_STEM,
                MissingWildsBlocks.FALLEN_MANGROVE_LOG,
                MissingWildsBlocks.FALLEN_CHERRY_LOG,
                MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM,
                MissingWildsBlocks.JAR_BLOCK,
                MissingWildsBlocks.FIREFLY_JAR_BLOCK
        );
        ModCompatHandler.getFallenLogBlocks().forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.cutoutMipped(), block));
        Services.PLATFORM.setBlockRenderType(RenderType.cutout(),
                MissingWildsBlocks.BLUE_FORGET_ME_NOT,
                MissingWildsBlocks.PURPLE_FORGET_ME_NOT,
                MissingWildsBlocks.PINK_FORGET_ME_NOT,
                MissingWildsBlocks.WHITE_FORGET_ME_NOT,
                MissingWildsBlocks.SWEETSPIRE,
                MissingWildsBlocks.POTTED_SWEETSPIRE,
                MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT,
                MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT,
                MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT,
                MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT,
                MissingWildsBlocks.FORGET_ME_NOT);
//                MissingWildsBlocks.WATERLILY_BLOCK);
        Services.PLATFORM.setBlockRenderType(RenderType.translucent(),
                MissingWildsBlocks.TINTED_JAR_BLOCK,
                MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK,
                MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK,
                MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK,
                MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK,
                MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.LIME_STAINED_JAR_BLOCK,
                MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.PINK_STAINED_JAR_BLOCK,
                MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK,
                MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK,
                MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK,
                MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK,
                MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK,
                MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK,
                MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.RED_STAINED_JAR_BLOCK,
                MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK,
                MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK,
                MissingWildsBlocks.FOOD_JAR_BLOCK,
                MissingWildsBlocks.TINTED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.WHITE_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.ORANGE_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.MAGENTA_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.YELLOW_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.LIME_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.PINK_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.GRAY_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.CYAN_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.PURPLE_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.BLUE_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.BROWN_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.GREEN_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.RED_STAINED_FOOD_JAR_BLOCK,
                MissingWildsBlocks.BLACK_STAINED_FOOD_JAR_BLOCK,
                // Potion Jars
                MissingWildsBlocks.POTION_JAR_BLOCK,
                MissingWildsBlocks.TINTED_POTION_JAR_BLOCK,
                MissingWildsBlocks.WHITE_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.ORANGE_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.MAGENTA_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_BLUE_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.YELLOW_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.LIME_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.PINK_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.GRAY_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.LIGHT_GRAY_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.CYAN_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.PURPLE_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.BLUE_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.BROWN_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.GREEN_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.RED_STAINED_POTION_JAR_BLOCK,
                MissingWildsBlocks.BLACK_STAINED_POTION_JAR_BLOCK
        );
        ModCompatHandler.getJarBlocks().values().forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));
        ModCompatHandler.getFireflyJarBlocks().values().forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));
        ModCompatHandler.getFoodJarBlocks().values().forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));


        ModCompatHandler.getModCompats().forEach(ModCompatInstance::clientInit);
    }

    public interface BlockEntityRendererBiConsumer {
        <T extends BlockEntity> void accept(BlockEntityType<? extends T> type, BlockEntityRendererProvider<? super T> factory);
    }

    public static void registerEntityRenderers(BlockEntityRendererBiConsumer biConsumer) {
        biConsumer.accept(MissingWildsBlockEntities.FOOD_JAR, FoodJarRenderer::new);
    }
}
