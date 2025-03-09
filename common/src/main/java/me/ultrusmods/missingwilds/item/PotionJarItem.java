package me.ultrusmods.missingwilds.item;

import me.ultrusmods.missingwilds.block.PotionJarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class PotionJarItem extends BlockItem {
    public PotionJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var potion = stack.get(MissingWildsDataComponents.POTION);
        if (potion != null) {
            PotionContents.addPotionTooltip(potion.value().getEffects(),
                            tooltipComponents::add,
                            1.0F, context.tickRate());
        }
        var blockState = stack.get(DataComponents.BLOCK_STATE);
        if (blockState != null && blockState.get(PotionJarBlock.POTION_LEVEL) != null) {
            tooltipComponents.add(Component.literal("(%d/3)".formatted(blockState.get(PotionJarBlock.POTION_LEVEL))).withStyle(ChatFormatting.WHITE));
        }

    }
}
