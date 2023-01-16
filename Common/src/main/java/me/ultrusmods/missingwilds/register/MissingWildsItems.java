package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.FireflyJarBlock;
import me.ultrusmods.missingwilds.item.FireflyJarItem;
import me.ultrusmods.missingwilds.item.MissingWildsFoodComponents;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static net.minecraft.world.item.Item.Properties;

public class MissingWildsItems {
	public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM_REGISTRY, Constants.MOD_ID);


	public static final RegistryObject<Item> FALLEN_OAK_LOG = register("fallen_oak_log", MissingWildsBlocks.FALLEN_OAK_LOG);
	public static final RegistryObject<Item> FALLEN_BIRCH_LOG = register("fallen_birch_log", MissingWildsBlocks.FALLEN_BIRCH_LOG);
	public static final RegistryObject<Item> FALLEN_SPRUCE_LOG = register("fallen_spruce_log", MissingWildsBlocks.FALLEN_SPRUCE_LOG);
	public static final RegistryObject<Item> FALLEN_JUNGLE_LOG = register("fallen_jungle_log", MissingWildsBlocks.FALLEN_JUNGLE_LOG);
	public static final RegistryObject<Item> FALLEN_DARK_OAK_LOG = register("fallen_dark_oak_log", MissingWildsBlocks.FALLEN_DARK_OAK_LOG);
	public static final RegistryObject<Item> FALLEN_ACACIA_LOG = register("fallen_acacia_log", MissingWildsBlocks.FALLEN_ACACIA_LOG);
	public static final RegistryObject<Item> FALLEN_MANGROVE_LOG = register("fallen_mangrove_log", MissingWildsBlocks.FALLEN_MANGROVE_LOG);
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
	public static final RegistryObject<Item> JAR = register("jar", () -> new BlockItem(MissingWildsBlocks.JAR_BLOCK.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	public static final RegistryObject<Item> FIREFLY_JAR = register("firefly_jar", () -> new FireflyJarItem(MissingWildsBlocks.FIREFLY_JAR_BLOCK.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	public static final RegistryObject<Item> TINTED_JAR = register("tinted_jar", () -> new BlockItem(MissingWildsBlocks.TINTED_JAR_BLOCK.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	public static final RegistryObject<Item> TINTED_FIREFLY_JAR = register("tinted_firefly_jar", () -> new FireflyJarItem(MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));

	public static final RegistryObject<Item> WHITE_STAINED_JAR_ITEM = register("white_stained_jar", MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> WHITE_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("white_stained_firefly_jar", MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> ORANGE_STAINED_JAR_ITEM = register("orange_stained_jar", MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> ORANGE_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("orange_stained_firefly_jar", MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> MAGENTA_STAINED_JAR_ITEM = register("magenta_stained_jar", MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> MAGENTA_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("magenta_stained_firefly_jar", MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> LIGHT_BLUE_STAINED_JAR_ITEM = register("light_blue_stained_jar", MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> LIGHT_BLUE_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("light_blue_stained_firefly_jar", MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> YELLOW_STAINED_JAR_ITEM = register("yellow_stained_jar", MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> YELLOW_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("yellow_stained_firefly_jar", MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> LIME_STAINED_JAR_ITEM = register("lime_stained_jar", MissingWildsBlocks.LIME_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> LIME_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("lime_stained_firefly_jar", MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> PINK_STAINED_JAR_ITEM = register("pink_stained_jar", MissingWildsBlocks.PINK_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> PINK_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("pink_stained_firefly_jar", MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> GRAY_STAINED_JAR_ITEM = register("gray_stained_jar", MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> GRAY_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("gray_stained_firefly_jar", MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> LIGHT_GRAY_STAINED_JAR_ITEM = register("light_gray_stained_jar", MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> LIGHT_GRAY_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("light_gray_stained_firefly_jar", MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> CYAN_STAINED_JAR_ITEM = register("cyan_stained_jar", MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> CYAN_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("cyan_stained_firefly_jar", MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> PURPLE_STAINED_JAR_ITEM = register("purple_stained_jar", MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> PURPLE_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("purple_stained_firefly_jar", MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> BLUE_STAINED_JAR_ITEM = register("blue_stained_jar", MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> BLUE_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("blue_stained_firefly_jar", MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> BROWN_STAINED_JAR_ITEM = register("brown_stained_jar", MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> BROWN_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("brown_stained_firefly_jar", MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> GREEN_STAINED_JAR_ITEM = register("green_stained_jar", MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> GREEN_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("green_stained_firefly_jar", MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> RED_STAINED_JAR_ITEM = register("red_stained_jar", MissingWildsBlocks.RED_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> RED_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("red_stained_firefly_jar", MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> BLACK_STAINED_JAR_ITEM = register("black_stained_jar", MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK);
	public static final RegistryObject<Item> BLACK_STAINED_FIREFLY_JAR_ITEM = registerFireflyJar("black_stained_firefly_jar", MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK);
	public static final RegistryObject<Item> FIREFLY_BOTTLE_ITEM = register("firefly_bottle", () -> new Item(new Item.Properties().tab(Services.PLATFORM.getCreativeTab())));
	public static void init() {

	}

	private static RegistryObject<Item> register(String id, Supplier<? extends Item> supplier) {
		return ITEMS.register(id, supplier);
	}
	public static RegistryObject<Item> register(String id, RegistryObject<Block> block) {
		return ITEMS.register(id,() -> new BlockItem(block.get(), new Properties().tab(Services.PLATFORM.getCreativeTab())));
	}
	public static RegistryObject<Item> registerFireflyJar(String id, RegistryObject<Block> block) {
		return ITEMS.register(id, () -> new FireflyJarItem(block.get(), new Item.Properties().tab(Services.PLATFORM.getCreativeTab())));
	}
}
