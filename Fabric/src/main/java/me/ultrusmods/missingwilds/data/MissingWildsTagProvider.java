package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class MissingWildsTagProvider{


    public static class MissingWildsItemTagProvider extends FabricTagProvider<Item> {

        public MissingWildsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.ITEM, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            getOrCreateTagBuilder(MissingWildsTags.FIREFLY_JARS)
                    .add(MissingWildsItems.FIREFLY_JAR.get())
                    .add(MissingWildsItems.TINTED_FIREFLY_JAR.get())
                    .add(MissingWildsItems.WHITE_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.ORANGE_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.MAGENTA_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.LIGHT_BLUE_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.YELLOW_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.LIME_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.PINK_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.GRAY_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.LIGHT_GRAY_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.CYAN_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.PURPLE_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.BLUE_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.BROWN_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.GREEN_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.RED_STAINED_FIREFLY_JAR_ITEM.get())
                    .add(MissingWildsItems.BLACK_STAINED_FIREFLY_JAR_ITEM.get());
        }
    }
    public static class MissingWildsBlockTagProvider extends FabricTagProvider<Block> {

        public MissingWildsBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BLOCK, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                    .add(MissingWildsBlocks.POTTED_SWEETSPIRE.get())
                    .add(MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT.get())
                    .add(MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT.get())
                    .add(MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT.get())
                    .add(MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT.get());
        }
    }
}
