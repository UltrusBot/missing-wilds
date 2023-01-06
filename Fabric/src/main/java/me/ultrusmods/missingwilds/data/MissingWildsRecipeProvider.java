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
}
