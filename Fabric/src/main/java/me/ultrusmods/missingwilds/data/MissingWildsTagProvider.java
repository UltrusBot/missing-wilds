package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class MissingWildsTagProvider{


    public static class MissingWildsItemTagProvider extends FabricTagProvider<Item> {

        public MissingWildsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.ITEM, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            getOrCreateTagBuilder(MissingWildsTags.FIREFLY_JARS)
                    .add(MissingWildsItems.FIREFLY_JAR)
                    .add(MissingWildsItems.TINTED_FIREFLY_JAR)
                    .add(MissingWildsItems.WHITE_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.ORANGE_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.MAGENTA_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.LIGHT_BLUE_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.YELLOW_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.LIME_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.PINK_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.GRAY_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.LIGHT_GRAY_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.CYAN_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.PURPLE_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.BLUE_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.BROWN_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.GREEN_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.RED_STAINED_FIREFLY_JAR_ITEM)
                    .add(MissingWildsItems.BLACK_STAINED_FIREFLY_JAR_ITEM);

            getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                    .add(MissingWildsItems.BLUE_FORGET_ME_NOT)
                    .add(MissingWildsItems.PINK_FORGET_ME_NOT)
                    .add(MissingWildsItems.PURPLE_FORGET_ME_NOT)
                    .add(MissingWildsItems.WHITE_FORGET_ME_NOT);

            getOrCreateTagBuilder(MissingWildsTags.FORGET_ME_NOTS)
                    .add(MissingWildsItems.BLUE_FORGET_ME_NOT)
                    .add(MissingWildsItems.PINK_FORGET_ME_NOT)
                    .add(MissingWildsItems.PURPLE_FORGET_ME_NOT)
                    .add(MissingWildsItems.WHITE_FORGET_ME_NOT);
        }

    }
    public static class MissingWildsBlockTagProvider extends FabricTagProvider<Block> {

        public MissingWildsBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BLOCK, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                    .add(MissingWildsBlocks.POTTED_SWEETSPIRE)
                    .add(MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT);

            getOrCreateTagBuilder(MissingWildsTags.FALLEN_LOGS)
                    .add(MissingWildsBlocks.FALLEN_OAK_LOG)
                    .add(MissingWildsBlocks.FALLEN_SPRUCE_LOG)
                    .add(MissingWildsBlocks.FALLEN_BIRCH_LOG)
                    .add(MissingWildsBlocks.FALLEN_JUNGLE_LOG)
                    .add(MissingWildsBlocks.FALLEN_ACACIA_LOG)
                    .add(MissingWildsBlocks.FALLEN_DARK_OAK_LOG)
                    .add(MissingWildsBlocks.FALLEN_MUSHROOM_STEM)
                    .add(MissingWildsBlocks.FALLEN_CRIMSON_STEM)
                    .add(MissingWildsBlocks.FALLEN_WARPED_STEM)
                    .add(MissingWildsBlocks.FALLEN_MANGROVE_LOG)
                    .add(MissingWildsBlocks.FALLEN_CHERRY_LOG);

            getOrCreateTagBuilder(MissingWildsTags.MOSS)
                    .add(Blocks.MOSS_BLOCK)
                    .add(Blocks.MOSS_CARPET)
                    .addOptional(ResourceLocation.fromNamespaceAndPath("ecologics", "moss_layer"));

            getOrCreateTagBuilder(MissingWildsTags.SNOW)
                    .add(Blocks.SNOW)
                    .add(Blocks.SNOW_BLOCK)
                    .add(Blocks.POWDER_SNOW);

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                    .addTag(MissingWildsTags.FALLEN_LOGS);
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                    .add(MissingWildsBlocks.BLUE_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.PINK_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.PURPLE_FORGET_ME_NOT)
                    .add(MissingWildsBlocks.WHITE_FORGET_ME_NOT);
            getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                    .add(MissingWildsBlocks.SWEETSPIRE);

        }
    }
}
