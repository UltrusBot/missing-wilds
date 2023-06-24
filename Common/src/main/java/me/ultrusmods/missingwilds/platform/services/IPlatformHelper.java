package me.ultrusmods.missingwilds.platform.services;

import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();


    void setBlockRenderType(RenderType layer, Block... blocks);

    void duringItemRegistering();

    void duringBlockRegistering();

    <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, Block... blocks);

    EntityType<FireflySwarm> createFirefly();

    default void registerItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(MissingWildsItems.FALLEN_OAK_LOG.get());
        output.accept(MissingWildsItems.FALLEN_BIRCH_LOG.get());
        output.accept(MissingWildsItems.FALLEN_SPRUCE_LOG.get());
        output.accept(MissingWildsItems.FALLEN_JUNGLE_LOG.get());
        output.accept(MissingWildsItems.FALLEN_DARK_OAK_LOG.get());
        output.accept(MissingWildsItems.FALLEN_ACACIA_LOG.get());
        output.accept(MissingWildsItems.FALLEN_MANGROVE_LOG.get());
        output.accept(MissingWildsItems.FALLEN_CRIMSON_STEM.get());
        output.accept(MissingWildsItems.FALLEN_WARPED_STEM.get());
        output.accept(MissingWildsItems.FALLEN_MUSHROOM_STEM.get());
        output.accept(MissingWildsItems.FALLEN_CHERRY_LOG.get());
        output.accept(MissingWildsItems.BLUE_FORGET_ME_NOT.get());
        output.accept(MissingWildsItems.PURPLE_FORGET_ME_NOT.get());
        output.accept(MissingWildsItems.PINK_FORGET_ME_NOT.get());
        output.accept(MissingWildsItems.WHITE_FORGET_ME_NOT.get());
        output.accept(MissingWildsItems.SWEETSPIRE.get());
        output.accept(MissingWildsItems.BROWN_POLYPORE_MUSHROOM.get());
        output.accept(MissingWildsItems.ROASTED_POLYPORE_MUSHROOM.get());
        output.accept(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get());
        output.accept(MissingWildsItems.JAR.get());
        output.accept(MissingWildsItems.TINTED_JAR.get());
        output.accept(MissingWildsItems.WHITE_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.ORANGE_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.MAGENTA_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.LIGHT_BLUE_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.YELLOW_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.LIME_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.PINK_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.GRAY_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.LIGHT_GRAY_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.CYAN_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.PURPLE_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.BLUE_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.BROWN_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.GREEN_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.RED_STAINED_JAR_ITEM.get());
        output.accept(MissingWildsItems.BLACK_STAINED_JAR_ITEM.get());
    }

    default Block getWaterlilyBlock(BlockBehaviour.Properties properties) {
        return new WaterlilyBlock(properties);
    }
}
