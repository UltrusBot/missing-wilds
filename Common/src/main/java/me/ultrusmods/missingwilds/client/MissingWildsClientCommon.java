package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.client.render.FoodJarRenderer;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

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
                MissingWildsBlocks.FALLEN_CHERRY_LOG.get(),
                MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM.get(),
                MissingWildsBlocks.JAR_BLOCK.get(),
                MissingWildsBlocks.FIREFLY_JAR_BLOCK.get()
        );
        Services.PLATFORM.getModCompatHandler().FALLEN_LOG_BLOCKS.stream().map(RegistryObject::get).forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.cutoutMipped(), block));
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
                MissingWildsBlocks.FORGET_ME_NOT.get());
//                MissingWildsBlocks.WATERLILY_BLOCK.get());
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
                MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get(),
                MissingWildsBlocks.FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.TINTED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.WHITE_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.ORANGE_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.MAGENTA_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.YELLOW_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.LIME_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.PINK_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.GRAY_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.CYAN_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.PURPLE_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.BLUE_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.BROWN_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.GREEN_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.RED_STAINED_FOOD_JAR_BLOCK.get(),
                MissingWildsBlocks.BLACK_STAINED_FOOD_JAR_BLOCK.get()
        );
        Services.PLATFORM.getModCompatHandler().getJarBlocks().values().stream().map(RegistryObject::get).forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));
        Services.PLATFORM.getModCompatHandler().getFireflyJarBlocks().values().stream().map(RegistryObject::get).forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));
        Services.PLATFORM.getModCompatHandler().getFoodJarBlocks().values().stream().map(RegistryObject::get).forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));


        Services.PLATFORM.getModCompatHandler().getModCompats().forEach(ModCompatInstance::clientInit);
    }

    public interface BlockEntityRendererBiConsumer {
        <T extends BlockEntity> void accept(BlockEntityType<? extends T> type, BlockEntityRendererProvider<? super T> factory);
    }

    public static void registerEntityRenderers(BlockEntityRendererBiConsumer biConsumer) {
        biConsumer.accept(MissingWildsBlockEntities.FOOD_JAR.get(), FoodJarRenderer::new);
    }
}
