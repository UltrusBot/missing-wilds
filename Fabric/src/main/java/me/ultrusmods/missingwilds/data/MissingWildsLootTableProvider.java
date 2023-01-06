package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.function.BiConsumer;

public class MissingWildsLootTableProvider extends SimpleFabricLootTableProvider {
    public MissingWildsLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextParamSets.BLOCK);
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        dropSelf("fallen_oak_log", MissingWildsBlocks.FALLEN_OAK_LOG.get(), biConsumer);
        dropSelf("fallen_birch_log", MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), biConsumer);
        dropSelf("fallen_spruce_log", MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), biConsumer);
        dropSelf("fallen_jungle_log", MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), biConsumer);
        dropSelf("fallen_acacia_log", MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), biConsumer);
        dropSelf("fallen_dark_oak_log", MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), biConsumer);
        dropSelf("fallen_crimson_stem", MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(), biConsumer);
        dropSelf("fallen_warped_stem", MissingWildsBlocks.FALLEN_WARPED_STEM.get(), biConsumer);
        dropSelf("mangrove_log", MissingWildsBlocks.FALLEN_MANGROVE_LOG.get(), biConsumer);
        dropSelf("mushroom_stem", MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get(), biConsumer);
    }

    public void dropSelf(String id, Block block, BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(Constants.id(id), BlockLoot.createSingleItemTable(block));
    }
}
