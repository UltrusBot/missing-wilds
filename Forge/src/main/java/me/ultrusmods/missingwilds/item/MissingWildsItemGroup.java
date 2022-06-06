package me.ultrusmods.missingwilds.item;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MissingWildsItemGroup extends CreativeModeTab {
    public static final MissingWildsItemGroup MISSING_WILDS = new MissingWildsItemGroup();

    public MissingWildsItemGroup() {
        super(Constants.MOD_ID + ".items");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get());
    }
}
