package me.ultrusmods.missingwilds.compat.bovines;

import house.greenhouse.bovinesandbuttercups.content.component.BovinesDataComponents;
import me.ultrusmods.missingwilds.block.PotionJarBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class NectarJarItem extends BlockItem {
    public NectarJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var itemNectar = stack.get(BovinesDataComponents.NECTAR);
        if (itemNectar != null) {
            itemNectar.addToTooltip(context, tooltipComponents::add, tooltipFlag);
        }
        var blockState = stack.get(DataComponents.BLOCK_STATE);
        if (blockState != null && blockState.get(PotionJarBlock.POTION_LEVEL) != null) {
            tooltipComponents.add(Component.literal("(%d/3)".formatted(blockState.get(NectarJarBlock.NECTAR_LEVEL))).withStyle(ChatFormatting.WHITE));
        }
    }
}
