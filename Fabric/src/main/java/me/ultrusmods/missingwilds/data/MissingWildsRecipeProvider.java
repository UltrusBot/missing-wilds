package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class MissingWildsRecipeProvider extends FabricRecipeProvider {


    public MissingWildsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_OAK_LOG, Blocks.OAK_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_BIRCH_LOG, Blocks.BIRCH_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_SPRUCE_LOG, Blocks.SPRUCE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_JUNGLE_LOG, Blocks.JUNGLE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_ACACIA_LOG, Blocks.ACACIA_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_DARK_OAK_LOG, Blocks.DARK_OAK_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_CRIMSON_STEM, Blocks.CRIMSON_STEM, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_WARPED_STEM, Blocks.WARPED_STEM, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_MANGROVE_LOG, Blocks.MANGROVE_LOG, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_MUSHROOM_STEM, Blocks.MUSHROOM_STEM, exporter);
        createFallenLogRecipe(MissingWildsBlocks.FALLEN_CHERRY_LOG, Blocks.CHERRY_LOG, exporter);
        createGlassJarRecipe(MissingWildsBlocks.JAR_BLOCK, Blocks.GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.TINTED_JAR_BLOCK, Blocks.TINTED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK, Blocks.WHITE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK, Blocks.ORANGE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK, Blocks.MAGENTA_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK, Blocks.LIGHT_BLUE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK, Blocks.YELLOW_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK, Blocks.LIME_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK, Blocks.PINK_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK, Blocks.GRAY_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK, Blocks.LIGHT_GRAY_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK, Blocks.CYAN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK, Blocks.PURPLE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK, Blocks.BLUE_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK, Blocks.BROWN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK, Blocks.GREEN_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.RED_STAINED_JAR_BLOCK, Blocks.RED_STAINED_GLASS, exporter);
        createGlassJarRecipe(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK, Blocks.BLACK_STAINED_GLASS, exporter);


        oneToOneConversionRecipe(exporter, Items.BLUE_DYE, MissingWildsItems.BLUE_FORGET_ME_NOT, "blue_dye");
        oneToOneConversionRecipe(exporter, Items.PINK_DYE, MissingWildsItems.PINK_FORGET_ME_NOT, "pink_dye");
        oneToOneConversionRecipe(exporter, Items.PURPLE_DYE, MissingWildsItems.PURPLE_FORGET_ME_NOT, "purple_dye");
        oneToOneConversionRecipe(exporter, Items.WHITE_DYE, MissingWildsItems.WHITE_FORGET_ME_NOT, "white_dye");

        oneToOneConversionRecipe(exporter, Items.LIGHT_GRAY_DYE, MissingWildsItems.SWEETSPIRE, "light_gray_dye", 2);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MissingWildsItems.BROWN_POLYPORE_MUSHROOM), RecipeCategory.FOOD, MissingWildsItems.ROASTED_POLYPORE_MUSHROOM, 0.35F, 200).unlockedBy("has_polypore_mushroom", has(MissingWildsItems.BROWN_POLYPORE_MUSHROOM)).save(exporter);
        simpleCookingRecipe(exporter, "cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 200, MissingWildsItems.BROWN_POLYPORE_MUSHROOM, MissingWildsItems.ROASTED_POLYPORE_MUSHROOM, 0.35F);
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, MissingWildsItems.BROWN_POLYPORE_MUSHROOM, MissingWildsItems.ROASTED_POLYPORE_MUSHROOM, 0.35F);
    }

    public void createFallenLogRecipe(Block fallenLog, Block baseLog, RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, fallenLog, 8)
                .pattern("LLL")
                .pattern("L L")
                .pattern("LLL")
                .define('L', baseLog)
                .unlockedBy("has_log", has(baseLog))
                .group("missingwilds:fallen_logs")
                .save(exporter);
    }



    public void createGlassJarRecipe(Block glassJar, Block baseGlass, RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, glassJar, 1)
                .pattern("GPG")
                .pattern("G G")
                .pattern("GGG")
                .define('G', baseGlass)
                .define('P', ItemTags.PLANKS)
                .unlockedBy("has_glass", has(baseGlass))
                .group("missingwilds:glass_jars")
                .save(exporter);
    }


}
