package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class MissingWildsRecipeProvider extends FabricRecipeProvider {
    public MissingWildsRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_OAK_LOG.get(), Blocks.OAK_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), Blocks.BIRCH_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), Blocks.SPRUCE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), Blocks.JUNGLE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), Blocks.ACACIA_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), Blocks.DARK_OAK_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(), Blocks.CRIMSON_STEM, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_WARPED_STEM.get(), Blocks.WARPED_STEM, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_MANGROVE_LOG.get(), Blocks.MANGROVE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get(), Blocks.MUSHROOM_STEM, exporter);
        createGlassJarRecipe(MissingWildsBlocks.JAR_BLOCK.get(), Blocks.GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.TINTED_JAR_BLOCK.get(), Blocks.TINTED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(), Blocks.WHITE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(), Blocks.ORANGE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(), Blocks.MAGENTA_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(), Blocks.LIGHT_BLUE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(), Blocks.YELLOW_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(), Blocks.LIME_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(), Blocks.PINK_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(), Blocks.GRAY_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(), Blocks.LIGHT_GRAY_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(), Blocks.CYAN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(), Blocks.PURPLE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(), Blocks.BLUE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(), Blocks.BROWN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(), Blocks.GREEN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(), Blocks.RED_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(), Blocks.BLACK_STAINED_GLASS, exporter);

    }
    public void createFallenLogRecipe(Block fallenLog, Block baseLog, Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(fallenLog, 8)
                .pattern("LLL")
                .pattern("L L")
                .pattern("LLL")
                .define('L', baseLog)
                .unlockedBy("has_log", has(baseLog))
                .group("missingwilds:fallen_logs")
                .save(exporter);
    }

    public void createGlassJarRecipe(Block glassJar, Block baseGlass, Consumer<FinishedRecipe> exporter) {
        ShapedRecipeBuilder.shaped(glassJar, 1)
                .pattern(" G ")
                .pattern("G G")
                .pattern("GGG")
                .define('G', baseGlass)
                .unlockedBy("has_glass", has(baseGlass))
                .group("missingwilds:glass_jars")
                .save(exporter);
    }
}
