package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.item.MissingWildsFoodComponents;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static net.minecraft.world.item.Item.*;

public class MissingWildsItems {
	public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);


	public static final RegistryObject<Item> FALLEN_OAK_LOG = register("fallen_oak_log", MissingWildsBlocks.FALLEN_OAK_LOG);
	public static final RegistryObject<Item> FALLEN_BIRCH_LOG = register("fallen_birch_log", MissingWildsBlocks.FALLEN_BIRCH_LOG);
	public static final RegistryObject<Item> FALLEN_SPRUCE_LOG = register("fallen_spruce_log", MissingWildsBlocks.FALLEN_SPRUCE_LOG);
	public static final RegistryObject<Item> FALLEN_JUNGLE_LOG = register("fallen_jungle_log", MissingWildsBlocks.FALLEN_JUNGLE_LOG);
	public static final RegistryObject<Item> FALLEN_DARK_OAK_LOG = register("fallen_dark_oak_log", MissingWildsBlocks.FALLEN_DARK_OAK_LOG);
	public static final RegistryObject<Item> FALLEN_ACACIA_LOG = register("fallen_acacia_log", MissingWildsBlocks.FALLEN_ACACIA_LOG);
	public static final RegistryObject<Item> FALLEN_CRIMSON_STEM = register("fallen_crimson_stem", MissingWildsBlocks.FALLEN_CRIMSON_STEM);
	public static final RegistryObject<Item> FALLEN_WARPED_STEM = register("fallen_warped_stem", MissingWildsBlocks.FALLEN_WARPED_STEM);
	public static final RegistryObject<Item> FALLEN_MUSHROOM_STEM = register("fallen_mushroom_stem", MissingWildsBlocks.FALLEN_MUSHROOM_STEM);
	public static final RegistryObject<Item> BLUE_FORGET_ME_NOT = register("blue_forget_me_not", MissingWildsBlocks.BLUE_FORGET_ME_NOT);
	public static final RegistryObject<Item> PURPLE_FORGET_ME_NOT = register("purple_forget_me_not", MissingWildsBlocks.PURPLE_FORGET_ME_NOT);
	public static final RegistryObject<Item> PINK_FORGET_ME_NOT = register("pink_forget_me_not", MissingWildsBlocks.PINK_FORGET_ME_NOT);
	public static final RegistryObject<Item> WHITE_FORGET_ME_NOT = register("white_forget_me_not", MissingWildsBlocks.WHITE_FORGET_ME_NOT);
	public static final RegistryObject<Item> BROWN_POLYPORE_MUSHROOM = register("brown_polypore_mushroom", MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM);
	public static final RegistryObject<Item> SWEETSPIRE = register("sweetspire", () -> new DoubleHighBlockItem(MissingWildsBlocks.SWEETSPIRE.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	public static final RegistryObject<Item> ROASTED_POLYPORE_MUSHROOM = register("roasted_polypore_mushroom", () -> new Item(new Properties().tab(Services.PLATFORM.getCreativeTab()).food(MissingWildsFoodComponents.ROASTED_POLYPORE)));
	public static void init() {

	}

	private static RegistryObject<Item> register(String id, Supplier<? extends Item> supplier) {
		return ITEMS.register(id, supplier);
	}
	public static RegistryObject<Item> register(String id, RegistryObject<Block> block) {
		return ITEMS.register(id,() -> new BlockItem(block.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	}
}
