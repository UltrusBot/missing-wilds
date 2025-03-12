package me.ultrusmods.missingwilds.compat;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public interface ModCompatClient {
    void init();

    default void registerBlockColors(BiConsumer<BlockColor, Block[]> registerBlockColors) {
        
    };
    default void registerItemColors(BiConsumer<ItemColor, ItemLike[]> registerItemColors) {
        
    };
    
    default void onTagLoad() {
        
    }
}
