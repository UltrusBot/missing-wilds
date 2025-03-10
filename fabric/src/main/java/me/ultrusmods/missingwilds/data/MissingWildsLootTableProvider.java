package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import me.ultrusmods.missingwilds.block.PotionJarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsDataComponents;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;


public class MissingWildsLootTableProvider extends FabricBlockLootTableProvider {

    protected MissingWildsLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        dropSelf(MissingWildsBlocks.FALLEN_OAK_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_BIRCH_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_SPRUCE_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_JUNGLE_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_ACACIA_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_DARK_OAK_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_CRIMSON_STEM);
        dropSelf(MissingWildsBlocks.FALLEN_WARPED_STEM);
        dropSelf(MissingWildsBlocks.FALLEN_MANGROVE_LOG);
        dropSelf(MissingWildsBlocks.FALLEN_MUSHROOM_STEM);
        dropSelf(MissingWildsBlocks.FALLEN_CHERRY_LOG);

        dropSelf(MissingWildsBlocks.JAR_BLOCK);
        dropSelf(MissingWildsBlocks.TINTED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.RED_STAINED_JAR_BLOCK);
        dropSelf(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK);

        dropFireflyJar(MissingWildsBlocks.FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK);
        dropFireflyJar(MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK);
        
        dropPotionJar(MissingWildsBlocks.POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.TINTED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.WHITE_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.ORANGE_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.MAGENTA_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.LIGHT_BLUE_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.YELLOW_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.LIME_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.PINK_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.GRAY_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.LIGHT_GRAY_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.CYAN_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.PURPLE_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.BLUE_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.BROWN_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.GREEN_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.RED_STAINED_POTION_JAR_BLOCK);
        dropPotionJar(MissingWildsBlocks.BLACK_STAINED_POTION_JAR_BLOCK);

        dropOther(MissingWildsBlocks.FOOD_JAR_BLOCK, MissingWildsBlocks.JAR_BLOCK);
        dropOther(MissingWildsBlocks.TINTED_FOOD_JAR_BLOCK, MissingWildsBlocks.TINTED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.WHITE_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.ORANGE_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.MAGENTA_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.YELLOW_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.LIME_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.LIME_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.PINK_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.PINK_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.GRAY_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.CYAN_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.PURPLE_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.BLUE_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.BROWN_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.GREEN_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.RED_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.RED_STAINED_JAR_BLOCK);
        dropOther(MissingWildsBlocks.BLACK_STAINED_FOOD_JAR_BLOCK, MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK);

        dropPottedContents(MissingWildsBlocks.POTTED_SWEETSPIRE);
        dropPottedContents(MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT);
        dropPottedContents(MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT);
        dropPottedContents(MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT);
        dropPottedContents(MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT);

        dropSelf(MissingWildsBlocks.BLUE_FORGET_ME_NOT);
        dropSelf(MissingWildsBlocks.PINK_FORGET_ME_NOT);
        dropSelf(MissingWildsBlocks.PURPLE_FORGET_ME_NOT);
        dropSelf(MissingWildsBlocks.WHITE_FORGET_ME_NOT);
        add(MissingWildsBlocks.SWEETSPIRE, p_250741_ -> this.createSinglePropConditionTable(p_250741_, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));

    }



    public void dropFireflyJar(Block block) {
        this.add(block, LootTable.lootTable()
                        .withPool(applyExplosionCondition(block,
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(block)
                                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                                .apply(CopyBlockState.copyState(block).copy(FireflyJarBlock.LIGHT_LEVEL))
                                                .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                        .include(MissingWildsDataComponents.COLOR)
                                                )))
                        ));
    }
    public void dropPotionJar(Block block) {
        this.add(block, LootTable.lootTable()
                .withPool(applyExplosionCondition(block,
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block)
                                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                        .apply(CopyBlockState.copyState(block).copy(PotionJarBlock.POTION_LEVEL))
                                        .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                .include(MissingWildsDataComponents.POTION)
                                        )))
                ));
    }
}
