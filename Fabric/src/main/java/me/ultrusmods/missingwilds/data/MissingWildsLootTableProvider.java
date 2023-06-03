package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

import static net.minecraft.data.loot.BlockLoot.applyExplosionCondition;

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

        dropSelf("jar", MissingWildsBlocks.JAR_BLOCK.get(), biConsumer);
        dropSelf("tinted_jar", MissingWildsBlocks.TINTED_JAR_BLOCK.get(), biConsumer);
        dropSelf("white_stained_jar", MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("orange_stained_jar", MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("magenta_stained_jar", MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("light_blue_stained_jar", MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("yellow_stained_jar", MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("lime_stained_jar", MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("pink_stained_jar", MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("gray_stained_jar", MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("light_gray_stained_jar", MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("cyan_stained_jar", MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("purple_stained_jar", MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("blue_stained_jar", MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("brown_stained_jar", MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("green_stained_jar", MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("red_stained_jar", MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(), biConsumer);
        dropSelf("black_stained_jar", MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(), biConsumer);

        dropBlockEntity("firefly_jar", MissingWildsBlocks.FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("tinted_firefly_jar", MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("white_stained_firefly_jar", MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("orange_stained_firefly_jar", MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("magenta_stained_firefly_jar", MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("light_blue_stained_firefly_jar", MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("yellow_stained_firefly_jar", MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("lime_stained_firefly_jar", MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("pink_stained_firefly_jar", MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("gray_stained_firefly_jar", MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("light_gray_stained_firefly_jar", MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("cyan_stained_firefly_jar", MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("purple_stained_firefly_jar", MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("blue_stained_firefly_jar", MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("brown_stained_firefly_jar", MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("green_stained_firefly_jar", MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("red_stained_firefly_jar", MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);
        dropBlockEntity("black_stained_firefly_jar", MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get(), biConsumer);

        dropOther("food_jar", MissingWildsBlocks.JAR_BLOCK.get(), biConsumer);
        dropOther("tinted_food_jar", MissingWildsBlocks.TINTED_JAR_BLOCK.get(), biConsumer);
        dropOther("white_stained_food_jar", MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("orange_stained_food_jar", MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("magenta_stained_food_jar", MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("light_blue_stained_food_jar", MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("yellow_stained_food_jar", MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("lime_stained_food_jar", MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("pink_stained_food_jar", MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("gray_stained_food_jar", MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("light_gray_stained_food_jar", MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("cyan_stained_food_jar", MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("purple_stained_food_jar", MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("blue_stained_food_jar", MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("brown_stained_food_jar", MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("green_stained_food_jar", MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("red_stained_food_jar", MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get(), biConsumer);
        dropOther("black_stained_food_jar", MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get(), biConsumer);
    }

    public void dropSelf(String id, Block block, BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(Constants.id("blocks/" +  id), BlockLoot.createSingleItemTable(block));
    }

    public void dropOther(String id, Block other, BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(Constants.id("blocks/" +  id), BlockLoot.createSingleItemTable(other));
    }

    public void dropBlockEntity(String id, Block block, BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(Constants.id("blocks/" +  id),
                LootTable.lootTable()
                        .withPool(applyExplosionCondition(block,
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(block)
                                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                                .apply(CopyBlockState.copyState(block).copy(FireflyJarBlock.LIGHT_LEVEL))
                                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                                        .copy("color", "BlockEntityTag.color")
                                        )))
        ));
    }
}
