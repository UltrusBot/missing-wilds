package me.ultrusmods.missingwilds.block.entity;

import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public interface ToolTipProvidingBlockEntity {
    
    void getTooltip(Consumer<Component> adder);
}
