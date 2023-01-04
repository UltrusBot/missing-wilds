package me.ultrusmods.missingwilds.item;

import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FireflyJarItem extends BlockItem {
    private static final int BAR_COLOR = Mth.color(.196f, .804f, .196f);

    public FireflyJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState state = super.getPlacementState(context);
        if (state != null) {
            var lightLevel = context.getItemInHand().getOrCreateTag().getInt("light_level");
            lightLevel = Math.max(1, Math.min(15, lightLevel));

            return state.setValue(FireflyJarBlock.LIGHT_LEVEL, lightLevel);
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.nullToEmpty("Light Level: " + stack.getOrCreateTag().getInt("light_level")));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * stack.getOrCreateTag().getInt("light_level") / 15, 13);
    }
}
