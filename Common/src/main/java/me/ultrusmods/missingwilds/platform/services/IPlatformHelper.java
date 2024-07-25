package me.ultrusmods.missingwilds.platform.services;

import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
        output.accept(MissingWildsItems.FALLEN_OAK_LOG);
        output.accept(MissingWildsItems.FALLEN_BIRCH_LOG);
        output.accept(MissingWildsItems.FALLEN_SPRUCE_LOG);
        output.accept(MissingWildsItems.FALLEN_JUNGLE_LOG);
        output.accept(MissingWildsItems.FALLEN_DARK_OAK_LOG);
        output.accept(MissingWildsItems.FALLEN_ACACIA_LOG);
        output.accept(MissingWildsItems.FALLEN_MANGROVE_LOG);
        output.accept(MissingWildsItems.FALLEN_CRIMSON_STEM);
        output.accept(MissingWildsItems.FALLEN_WARPED_STEM);
        output.accept(MissingWildsItems.FALLEN_MUSHROOM_STEM);
        output.accept(MissingWildsItems.FALLEN_CHERRY_LOG);
        ModCompatHandler.getFallenLogItems().values().forEach(output::accept);
        output.accept(MissingWildsItems.BLUE_FORGET_ME_NOT);
        output.accept(MissingWildsItems.PURPLE_FORGET_ME_NOT);
        output.accept(MissingWildsItems.PINK_FORGET_ME_NOT);
        output.accept(MissingWildsItems.WHITE_FORGET_ME_NOT);
        output.accept(MissingWildsItems.SWEETSPIRE);
        output.accept(MissingWildsItems.BROWN_POLYPORE_MUSHROOM);
        output.accept(MissingWildsItems.ROASTED_POLYPORE_MUSHROOM);
        output.accept(MissingWildsItems.FIREFLY_BOTTLE_ITEM);
        output.accept(MissingWildsItems.JAR);
        output.accept(MissingWildsItems.TINTED_JAR);
        output.accept(MissingWildsItems.WHITE_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.ORANGE_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.MAGENTA_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.LIGHT_BLUE_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.YELLOW_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.LIME_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.PINK_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.GRAY_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.LIGHT_GRAY_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.CYAN_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.PURPLE_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.BLUE_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.BROWN_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.GREEN_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.RED_STAINED_JAR_ITEM);
        output.accept(MissingWildsItems.BLACK_STAINED_JAR_ITEM);
        ModCompatHandler.getJarBlocks().values().forEach(output::accept);
        ModCompatHandler.OTHER_ITEMS_TO_ITEM_GROUPS.forEach(output::accept);

    }
}
