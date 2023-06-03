package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.block.JarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nullable;

public class MissingWildsModelProvider extends FabricModelProvider {

    public MissingWildsModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        createFallenLog(MissingWildsBlocks.FALLEN_OAK_LOG.get(), Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(), Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_WARPED_STEM.get(), Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, blockModelGenerators);
        createFallenLog(MissingWildsBlocks.FALLEN_MANGROVE_LOG.get(), Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG, blockModelGenerators);

        createJar(MissingWildsBlocks.JAR_BLOCK.get(), Blocks.GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.FIREFLY_JAR_BLOCK.get(), Blocks.GLASS, blockModelGenerators, MissingWildsBlocks.JAR_BLOCK.get());
        createJar(MissingWildsBlocks.TINTED_JAR_BLOCK.get(), Blocks.TINTED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get(), Blocks.TINTED_GLASS, blockModelGenerators, MissingWildsBlocks.TINTED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(), Blocks.WHITE_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.WHITE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(), Blocks.ORANGE_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.ORANGE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(), Blocks.MAGENTA_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.MAGENTA_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(), Blocks.LIGHT_BLUE_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.LIGHT_BLUE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(), Blocks.YELLOW_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.YELLOW_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(), Blocks.LIME_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.LIME_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(), Blocks.PINK_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.PINK_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(), Blocks.GRAY_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.GRAY_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(), Blocks.LIGHT_GRAY_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.LIGHT_GRAY_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(), Blocks.CYAN_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.CYAN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(), Blocks.PURPLE_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.PURPLE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(), Blocks.BLUE_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.BLUE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(), Blocks.BROWN_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.BROWN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(), Blocks.GREEN_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.GREEN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(), Blocks.RED_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.RED_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(), Blocks.BLACK_STAINED_GLASS, blockModelGenerators, null);
        createJar(MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get(), Blocks.BLACK_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get());

        createJar(MissingWildsBlocks.FOOD_JAR_BLOCK.get(), Blocks.GLASS, blockModelGenerators, MissingWildsBlocks.JAR_BLOCK.get());
        createJar(MissingWildsBlocks.TINTED_FOOD_JAR_BLOCK.get(), Blocks.TINTED_GLASS, blockModelGenerators, MissingWildsBlocks.TINTED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.WHITE_STAINED_FOOD_JAR_BLOCK.get(), Blocks.WHITE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.ORANGE_STAINED_FOOD_JAR_BLOCK.get(), Blocks.ORANGE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.MAGENTA_STAINED_FOOD_JAR_BLOCK.get(), Blocks.MAGENTA_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK.get(), Blocks.LIGHT_BLUE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.YELLOW_STAINED_FOOD_JAR_BLOCK.get(), Blocks.YELLOW_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIME_STAINED_FOOD_JAR_BLOCK.get(), Blocks.LIME_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.PINK_STAINED_FOOD_JAR_BLOCK.get(), Blocks.PINK_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.GRAY_STAINED_FOOD_JAR_BLOCK.get(), Blocks.GRAY_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK.get(), Blocks.LIGHT_GRAY_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.CYAN_STAINED_FOOD_JAR_BLOCK.get(), Blocks.CYAN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.PURPLE_STAINED_FOOD_JAR_BLOCK.get(), Blocks.PURPLE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BLUE_STAINED_FOOD_JAR_BLOCK.get(), Blocks.BLUE_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BROWN_STAINED_FOOD_JAR_BLOCK.get(), Blocks.BROWN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.GREEN_STAINED_FOOD_JAR_BLOCK.get(), Blocks.GREEN_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.RED_STAINED_FOOD_JAR_BLOCK.get(), Blocks.RED_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get());
        createJar(MissingWildsBlocks.BLACK_STAINED_FOOD_JAR_BLOCK.get(), Blocks.BLACK_STAINED_GLASS, blockModelGenerators, MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get());






    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    public void createFallenLog(Block fallenLog, Block log, Block innerLog, BlockModelGenerators blockModelGenerators) {
        TextureMapping textureMapping = MissingWildsTextureMappings.createFallenLog(log, innerLog);
        var fallenLogModel = MissingWildsModelTemplates.FALLEN_LOG.create(fallenLog, textureMapping, blockModelGenerators.modelOutput);
        var fallenLogModelMossy = MissingWildsModelTemplates.FALLEN_LOG_MOSSY.create(fallenLog, textureMapping, blockModelGenerators.modelOutput);
        var fallenLogModelSnowy = MissingWildsModelTemplates.FALLEN_LOG_SNOWY.create(fallenLog, textureMapping, blockModelGenerators.modelOutput);
        var multiVariant = MultiVariantGenerator.multiVariant(fallenLog).with(
                PropertyDispatch.properties(BlockStateProperties.AXIS, FallenLogBlock.COVER)
                        .select(Direction.Axis.X, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogModel).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Y, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogModel).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Z, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogModel))
                        .select(Direction.Axis.X, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelMossy).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Y, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelMossy).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Z, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelMossy))
                        .select(Direction.Axis.X, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelSnowy).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Y, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelSnowy).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Z, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogModelSnowy))
        );
        blockModelGenerators.blockStateOutput.accept(multiVariant);


    }

    public void createJar(Block jar, Block glass, BlockModelGenerators blockModelGenerators, @Nullable Block originalJar) {
        TextureMapping textureMapping = MissingWildsTextureMappings.createJar(originalJar == null ? jar : originalJar, glass);
        var jarModel = MissingWildsModelTemplates.GLASS_JAR.create(jar, textureMapping, blockModelGenerators.modelOutput);
        var jarOpenModel = MissingWildsModelTemplates.GLASS_JAR_OPEN.create(jar, textureMapping, blockModelGenerators.modelOutput);
        var multiVariant = MultiVariantGenerator.multiVariant(jar).with(
                PropertyDispatch.property(JarBlock.COVERED)
                        .select(false, Variant.variant().with(VariantProperties.MODEL, jarOpenModel))
                        .select(true, Variant.variant().with(VariantProperties.MODEL, jarModel)));
        blockModelGenerators.blockStateOutput.accept(multiVariant);
    }

}
