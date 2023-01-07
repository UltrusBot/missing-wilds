package me.ultrusmods.missingwilds.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class FireflyJarItem extends BlockItem {
    private static final int BAR_COLOR = Mth.color(.196f, .804f, .196f);
    private static final Component BAR_TEXT = Component.translatable("tooltip.missingwilds.firefly_jar");

    public FireflyJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        int lightLevel = 1;
        if (stack.hasTag()) {
            lightLevel = Integer.parseInt(stack.getOrCreateTag().getCompound("BlockStateTag").getString("light_level"));
        }
        tooltip.add(Component.translatable("tooltip.missingwilds.firefly_jar", lightLevel).withStyle(ChatFormatting.GRAY));
    }

}
