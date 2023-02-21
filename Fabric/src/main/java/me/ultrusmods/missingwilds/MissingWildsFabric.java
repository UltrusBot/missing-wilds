package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import me.ultrusmods.missingwilds.worldgen.MissingWildsWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class MissingWildsFabric implements ModInitializer {
    public static final List<Block> COMPAT_LOGS = new ArrayList<>();
    public static final CreativeModeTab MISSING_WILD_ITEMS = FabricItemGroup.builder(
                    new ResourceLocation(Constants.MOD_ID, "items"))
            .icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()))
            .displayItems((featureFlagSet, output, bl) -> {
                output.accept(MissingWildsItems.FALLEN_OAK_LOG.get());
                output.accept(MissingWildsItems.FALLEN_BIRCH_LOG.get());
                output.accept(MissingWildsItems.FALLEN_SPRUCE_LOG.get());
                output.accept(MissingWildsItems.FALLEN_JUNGLE_LOG.get());
                output.accept(MissingWildsItems.FALLEN_DARK_OAK_LOG.get());
                output.accept(MissingWildsItems.FALLEN_ACACIA_LOG.get());
                output.accept(MissingWildsItems.FALLEN_MANGROVE_LOG.get());
                output.accept(MissingWildsItems.FALLEN_CRIMSON_STEM.get());
                output.accept(MissingWildsItems.FALLEN_WARPED_STEM.get());
                output.accept(MissingWildsItems.FALLEN_MUSHROOM_STEM.get());
                output.accept(MissingWildsItems.BLUE_FORGET_ME_NOT.get());
                output.accept(MissingWildsItems.PURPLE_FORGET_ME_NOT.get());
                output.accept(MissingWildsItems.PINK_FORGET_ME_NOT.get());
                output.accept(MissingWildsItems.WHITE_FORGET_ME_NOT.get());
                output.accept(MissingWildsItems.SWEETSPIRE.get());
                output.accept(MissingWildsItems.BROWN_POLYPORE_MUSHROOM.get());
                output.accept(MissingWildsItems.ROASTED_POLYPORE_MUSHROOM.get());
            })
            .build();

    @Override
    public void onInitialize() {
        MissingWildsResources.init();
        MissingWildsModCommon.init();
        ModCompat.checkModCompat();
        MissingWildsWorldGen.init();

    }
}
