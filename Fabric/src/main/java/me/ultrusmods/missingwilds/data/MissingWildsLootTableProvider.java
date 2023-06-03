package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


public class MissingWildsLootTableProvider extends FabricBlockLootTableProvider {


    protected MissingWildsLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }


    public void generate() {
        dropSelf(MissingWildsBlocks.FALLEN_OAK_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_BIRCH_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_ACACIA_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_CRIMSON_STEM.get());
        dropSelf(MissingWildsBlocks.FALLEN_WARPED_STEM.get());
        dropSelf(MissingWildsBlocks.FALLEN_MANGROVE_LOG.get());
        dropSelf(MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get());

        dropSelf(MissingWildsBlocks.JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.TINTED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get());
        dropSelf( MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get());
        dropSelf(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get());

        dropFireflyJar(MissingWildsBlocks.FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get());
        dropFireflyJar(MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get());

        dropOther(MissingWildsBlocks.FOOD_JAR_BLOCK.get(), MissingWildsBlocks.JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.TINTED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.TINTED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.WHITE_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.ORANGE_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.MAGENTA_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.YELLOW_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.LIME_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.LIME_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.PINK_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.PINK_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.GRAY_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.CYAN_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.PURPLE_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.BLUE_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.BROWN_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.GREEN_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.RED_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.RED_STAINED_JAR_BLOCK.get());
        dropOther(MissingWildsBlocks.BLACK_STAINED_FOOD_JAR_BLOCK.get(), MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK.get());
    }



    public void dropFireflyJar(Block block) {
        this.add(block, LootTable.lootTable()
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
