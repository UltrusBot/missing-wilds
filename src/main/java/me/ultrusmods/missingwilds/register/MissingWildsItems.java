package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.item.MissingWildsFoodComponents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.registry.Registry;

public class MissingWildsItems {
	public static final Item FALLEN_OAK_LOG = register("fallen_oak_log", MissingWildsBlocks.FALLEN_OAK_LOG);
	public static final Item FALLEN_BIRCH_LOG = register("fallen_birch_log", MissingWildsBlocks.FALLEN_BIRCH_LOG);
	public static final Item FALLEN_SPRUCE_LOG = register("fallen_spruce_log", MissingWildsBlocks.FALLEN_SPRUCE_LOG);
	public static final Item FALLEN_JUNGLE_LOG = register("fallen_jungle_log", MissingWildsBlocks.FALLEN_JUNGLE_LOG);
	public static final Item FALLEN_DARK_OAK_LOG = register("fallen_dark_oak_log", MissingWildsBlocks.FALLEN_DARK_OAK_LOG);
	public static final Item FALLEN_ACACIA_LOG = register("fallen_acacia_log", MissingWildsBlocks.FALLEN_ACACIA_LOG);
	public static final Item FALLEN_CRIMSON_STEM = register("fallen_crimson_stem", MissingWildsBlocks.FALLEN_CRIMSON_STEM);
	public static final Item FALLEN_WARPED_STEM = register("fallen_warped_stem", MissingWildsBlocks.FALLEN_WARPED_STEM);
	public static final Item FALLEN_MUSHROOM_STEM = register("fallen_mushroom_stem", MissingWildsBlocks.FALLEN_MUSHROOM_STEM);
	public static final Item BLUE_FORGET_ME_NOT = register("blue_forget_me_not", MissingWildsBlocks.BLUE_FORGET_ME_NOT);
	public static final Item PURPLE_FORGET_ME_NOT = register("purple_forget_me_not", MissingWildsBlocks.PURPLE_FORGET_ME_NOT);
	public static final Item PINK_FORGET_ME_NOT = register("pink_forget_me_not", MissingWildsBlocks.PINK_FORGET_ME_NOT);
	public static final Item WHITE_FORGET_ME_NOT = register("white_forget_me_not", MissingWildsBlocks.WHITE_FORGET_ME_NOT);
	public static final Item BROWN_POLYPORE_MUSHROOM = register("brown_polypore_mushroom", MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM);
	public static final Item SWEETSPIRE = register("sweetspire", new TallBlockItem(MissingWildsBlocks.SWEETSPIRE, new FabricItemSettings().group(MissingWildsMod.MISSING_WILD_ITEMS)));
	public static final Item ROASTED_POLYPORE_MUSHROOM = register("roasted_polypore_mushroom", new Item(new FabricItemSettings().group(MissingWildsMod.MISSING_WILD_ITEMS).food(MissingWildsFoodComponents.ROASTED_POLYPORE)));

	public static void init() {

	}

	private static Item register(String id, Item item) {
		return Registry.register(Registry.ITEM, MissingWildsMod.id(id), item);
	}
	public static Item register(String id, Block block) {
		return Registry.register(Registry.ITEM, MissingWildsMod.id(id), new BlockItem(block, new FabricItemSettings().group(MissingWildsMod.MISSING_WILD_ITEMS)));
	}
}
