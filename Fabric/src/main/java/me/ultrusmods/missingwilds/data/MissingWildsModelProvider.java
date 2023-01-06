package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
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
}
