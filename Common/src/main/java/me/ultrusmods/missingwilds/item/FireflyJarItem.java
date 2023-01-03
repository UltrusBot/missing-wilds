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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireflyJarItem extends BlockItem {
    private static final int BAR_COLOR = Mth.color(.196f, .804f, .196f);

    public FireflyJarItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(@NotNull BlockPlaceContext context) {
        BlockState state = super.getPlacementState(context);
        if (state != null) {
            return state.setValue(FireflyJarBlock.LIGHT_LEVEL, context.getItemInHand().getOrCreateTag().getInt("light_level"));
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.nullToEmpty("Light Level: " + stack.getOrCreateTag().getInt("light_level")));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * stack.getOrCreateTag().getInt("light_level") / 15, 13);
    }
}
