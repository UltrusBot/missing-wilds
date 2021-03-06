package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsFabric implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static final CreativeModeTab MISSING_WILD_ITEMS = FabricItemGroupBuilder.create(
                    new ResourceLocation(Constants.MOD_ID, "items"))
            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
            .build();

    @Override
    public void onInitialize() {
        MissingWildsResources.init();
        MissingWildsModCommon.init();
        ModCompat.checkModCompat();
        MissingWildsWorldGen.init();

    }
}
