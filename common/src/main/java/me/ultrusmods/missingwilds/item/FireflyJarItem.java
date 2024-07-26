package me.ultrusmods.missingwilds.item;

import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class FireflyJarItem extends BlockItem {
    public FireflyJarItem(Block block, Properties properties) {
        super(block, properties);
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        int lightLevel = 1;
        var components = stack.getComponents();
        var blockState = components.get(DataComponents.BLOCK_STATE);
//        if (stack.hasTag() && stack.getOrCreateTag().getCompound("BlockStateTag").contains("light_level")) {
//            lightLevel = Integer.parseInt(stack.getOrCreateTag().getCompound("BlockStateTag").getString("light_level"));
//        }
        if (blockState != null && blockState.get(FireflyJarBlock.LIGHT_LEVEL) != null) {
            lightLevel = blockState.get(FireflyJarBlock.LIGHT_LEVEL);
        }
        tooltipComponents.add(Component.translatable("tooltip.missingwilds.firefly_jar", lightLevel).withStyle(ChatFormatting.GRAY));
    }

    public static void increaseLightLevel(ItemStack stack, int amount) {
        int lightLevel = 0;
        if (stack.getComponents().get(DataComponents.BLOCK_STATE) != null) {
            lightLevel = stack.getComponents().get(DataComponents.BLOCK_STATE).get(FireflyJarBlock.LIGHT_LEVEL);
        }
        lightLevel = Mth.clamp(lightLevel + amount, 1, 15);
        stack.set(DataComponents.BLOCK_STATE, stack.getComponents().get(DataComponents.BLOCK_STATE).with(FireflyJarBlock.LIGHT_LEVEL, lightLevel));
    }

}
