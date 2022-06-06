package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MissingWildsMod implements ModInitializer {


    public static final CreativeModeTab MISSING_WILD_ITEMS = FabricItemGroupBuilder.create(
                    new ResourceLocation(Constants.MOD_ID, "items"))
            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
            .build();

    @Override
    public void onInitialize() {
        MissingWildsModCommon.init();
        MissingWildsWorldGen.init();
    }
}
