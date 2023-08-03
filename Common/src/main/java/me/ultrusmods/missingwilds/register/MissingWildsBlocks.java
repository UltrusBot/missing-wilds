package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.*;
import me.ultrusmods.missingwilds.compat.RegisteringModCompat;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class MissingWildsBlocks {
	public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MOD_ID);

	public static final RegistryObject<Block> FALLEN_OAK_LOG = registerFallenLog("fallen_oak_log");
	public static final RegistryObject<Block> FALLEN_BIRCH_LOG = registerFallenLog("fallen_birch_log");
	public static final RegistryObject<Block> FALLEN_SPRUCE_LOG = registerFallenLog("fallen_spruce_log");
	public static final RegistryObject<Block> FALLEN_JUNGLE_LOG = registerFallenLog("fallen_jungle_log");
	public static final RegistryObject<Block> FALLEN_DARK_OAK_LOG = registerFallenLog("fallen_dark_oak_log");
	public static final RegistryObject<Block> FALLEN_ACACIA_LOG = registerFallenLog("fallen_acacia_log");
	public static final RegistryObject<Block> FALLEN_MANGROVE_LOG = registerFallenLog("fallen_mangrove_log");
	public static final RegistryObject<Block> FALLEN_CRIMSON_STEM = registerFallenLog("fallen_crimson_stem");
	public static final RegistryObject<Block> FALLEN_WARPED_STEM = registerFallenLog("fallen_warped_stem");
	public static final RegistryObject<Block> FALLEN_MUSHROOM_STEM = registerFallenLog("fallen_mushroom_stem");
	public static final RegistryObject<Block> FALLEN_CHERRY_LOG = registerFallenLog("fallen_cherry_log");

	public static final RegistryObject<Block> BLUE_FORGET_ME_NOT = registerForgetMeNot("blue_forget_me_not", CombinedStackingFlowerBlock.FlowerType.BLUE);
	public static final RegistryObject<Block> PURPLE_FORGET_ME_NOT = registerForgetMeNot("purple_forget_me_not", CombinedStackingFlowerBlock.FlowerType.PURPLE);
	public static final RegistryObject<Block> PINK_FORGET_ME_NOT = registerForgetMeNot("pink_forget_me_not", CombinedStackingFlowerBlock.FlowerType.PINK);
	public static final RegistryObject<Block> WHITE_FORGET_ME_NOT = registerForgetMeNot("white_forget_me_not", CombinedStackingFlowerBlock.FlowerType.WHITE);

	public static final RegistryObject<Block> BROWN_POLYPORE_MUSHROOM = registerPolypore("brown_polypore_mushroom");

	public static final RegistryObject<Block> SWEETSPIRE = register("sweetspire",() -> new TallFlowerBlock(Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> POTTED_SWEETSPIRE = register("potted_sweetspire", () -> new FlowerPotBlock(SWEETSPIRE.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> POTTED_BLUE_FORGET_ME_NOT = register("potted_blue_forget_me_not", () -> new FlowerPotBlock(BLUE_FORGET_ME_NOT.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> POTTED_PURPLE_FORGET_ME_NOT = register("potted_purple_forget_me_not", () -> new FlowerPotBlock(PURPLE_FORGET_ME_NOT.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> POTTED_PINK_FORGET_ME_NOT = register("potted_pink_forget_me_not", () -> new FlowerPotBlock(PINK_FORGET_ME_NOT.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> POTTED_WHITE_FORGET_ME_NOT = register("potted_white_forget_me_not", () ->new FlowerPotBlock(WHITE_FORGET_ME_NOT.get(), Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> JAR_BLOCK = register("jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> FIREFLY_JAR_BLOCK = register("firefly_jar", MissingWildsBlocks::createFireflyJarBlock);

	public static final RegistryObject<Block> TINTED_JAR_BLOCK = register("tinted_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> TINTED_FIREFLY_JAR_BLOCK = register("tinted_firefly_jar", () -> new FireflyJarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> WHITE_STAINED_JAR_BLOCK = register("white_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> WHITE_STAINED_FIREFLY_JAR_BLOCK = register("white_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> ORANGE_STAINED_JAR_BLOCK = register("orange_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> ORANGE_STAINED_FIREFLY_JAR_BLOCK = register("orange_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> MAGENTA_STAINED_JAR_BLOCK = register("magenta_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> MAGENTA_STAINED_FIREFLY_JAR_BLOCK = register("magenta_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_JAR_BLOCK = register("light_blue_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK = register("light_blue_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> YELLOW_STAINED_JAR_BLOCK = register("yellow_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> YELLOW_STAINED_FIREFLY_JAR_BLOCK = register("yellow_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> LIME_STAINED_JAR_BLOCK = register("lime_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> LIME_STAINED_FIREFLY_JAR_BLOCK = register("lime_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> PINK_STAINED_JAR_BLOCK = register("pink_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> PINK_STAINED_FIREFLY_JAR_BLOCK = register("pink_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> GRAY_STAINED_JAR_BLOCK = register("gray_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> GRAY_STAINED_FIREFLY_JAR_BLOCK = register("gray_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_JAR_BLOCK = register("light_gray_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK = register("light_gray_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> CYAN_STAINED_JAR_BLOCK = register("cyan_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> CYAN_STAINED_FIREFLY_JAR_BLOCK = register("cyan_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> PURPLE_STAINED_JAR_BLOCK = register("purple_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> PURPLE_STAINED_FIREFLY_JAR_BLOCK = register("purple_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> BLUE_STAINED_JAR_BLOCK = register("blue_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> BLUE_STAINED_FIREFLY_JAR_BLOCK = register("blue_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> BROWN_STAINED_JAR_BLOCK = register("brown_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> BROWN_STAINED_FIREFLY_JAR_BLOCK = register("brown_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> GREEN_STAINED_JAR_BLOCK = register("green_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> GREEN_STAINED_FIREFLY_JAR_BLOCK = register("green_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> RED_STAINED_JAR_BLOCK = register("red_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> RED_STAINED_FIREFLY_JAR_BLOCK = register("red_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final RegistryObject<Block> BLACK_STAINED_JAR_BLOCK = register("black_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final RegistryObject<Block> BLACK_STAINED_FIREFLY_JAR_BLOCK = register("black_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);

	public static final RegistryObject<Block> FORGET_ME_NOT = register("forget_me_not", () -> new CombinedStackingFlowerBlock(MobEffects.LUCK, 10, Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE).pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> FOOD_JAR_BLOCK = register("food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> TINTED_FOOD_JAR_BLOCK = register("tinted_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> WHITE_STAINED_FOOD_JAR_BLOCK = register("white_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> ORANGE_STAINED_FOOD_JAR_BLOCK = register("orange_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> MAGENTA_STAINED_FOOD_JAR_BLOCK = register("magenta_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK = register("light_blue_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> YELLOW_STAINED_FOOD_JAR_BLOCK = register("yellow_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> LIME_STAINED_FOOD_JAR_BLOCK = register("lime_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> PINK_STAINED_FOOD_JAR_BLOCK = register("pink_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> GRAY_STAINED_FOOD_JAR_BLOCK = register("gray_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK = register("light_gray_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> CYAN_STAINED_FOOD_JAR_BLOCK = register("cyan_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> PURPLE_STAINED_FOOD_JAR_BLOCK = register("purple_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> BLUE_STAINED_FOOD_JAR_BLOCK = register("blue_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> BROWN_STAINED_FOOD_JAR_BLOCK = register("brown_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> GREEN_STAINED_FOOD_JAR_BLOCK = register("green_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> RED_STAINED_FOOD_JAR_BLOCK = register("red_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final RegistryObject<Block> BLACK_STAINED_FOOD_JAR_BLOCK = register("black_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);



//	public static final RegistryObject<Block> WATERLILY_BLOCK = register("waterlily", () -> Services.PLATFORM.getWaterlilyBlock(Properties.copy(Blocks.LILY_PAD)));

	public static void init() {
		Services.PLATFORM.getModCompatHandler().getModCompats().forEach(modCompat -> {
			if (modCompat instanceof RegisteringModCompat) {
				((RegisteringModCompat) modCompat).registerBlocks();
			}
		});
	}

	public static RegistryObject<Block> register(String id, Supplier<? extends Block> supplier) {
		return BLOCKS.register(id, supplier);
	}
	public static RegistryObject<Block> registerFallenLog(String id) {
		return register(id, () -> new FallenLogBlock(Properties.of().strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
	}

	private static RegistryObject<Block> registerForgetMeNot(String id, CombinedStackingFlowerBlock.FlowerType type) {
		return register(id, () -> new StackingFlowerBlock(MobEffects.LUCK, 10, Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE).pushReaction(PushReaction.DESTROY), type));
	}

	private static RegistryObject<Block> registerPolypore(String id) {
		return register(id, () -> new PolyporeMushroomBlock(Properties.of().mapColor(MapColor.DIRT).strength(0.2F).sound(SoundType.WOOD).noOcclusion()));
	}
	public static Block createJarBlock() {
		return new JarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion());
	}
	public static Block createFoodJarBlock() {
		return new FoodJarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion());
	}
	public static Block createFireflyJarBlock() {
		return new FireflyJarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion().lightLevel((state) -> state.getValue(FireflyJarBlock.LIGHT_LEVEL)));
	}
}
