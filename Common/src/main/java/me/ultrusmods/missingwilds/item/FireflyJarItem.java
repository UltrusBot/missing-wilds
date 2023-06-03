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
    public FireflyJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        int lightLevel = 1;
        if (stack.hasTag() && stack.getOrCreateTag().getCompound("BlockStateTag").contains("light_level")) {
            lightLevel = Integer.parseInt(stack.getOrCreateTag().getCompound("BlockStateTag").getString("light_level"));
        }
        tooltip.add(Component.translatable("tooltip.missingwilds.firefly_jar", lightLevel).withStyle(ChatFormatting.GRAY));
    }

    public static void increaseLightLevel(ItemStack stack, int amount) {
        int lightLevel = 0;
        if (stack.hasTag()) {
            lightLevel = Integer.parseInt(stack.getOrCreateTag().getCompound("BlockStateTag").getString("light_level"));
        }
        lightLevel = Mth.clamp(lightLevel + amount, 1, 15);
        var tag = stack.getOrCreateTag();
        var compound = tag.getCompound("BlockStateTag");
        compound.putString("light_level", "" + lightLevel);
        tag.put("BlockStateTag", compound);
        stack.setTag(tag);
    }

}
