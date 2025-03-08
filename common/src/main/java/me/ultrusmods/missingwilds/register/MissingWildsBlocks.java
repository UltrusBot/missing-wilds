package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.*;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.RegisteringModCompat;
import me.ultrusmods.missingwilds.data.LogData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class MissingWildsBlocks {
	public static final Map<String, Block> BLOCKS = new HashMap<>();

	public static final Block FALLEN_OAK_LOG = registerFallenLog("fallen_oak_log");
	public static final Block FALLEN_BIRCH_LOG = registerFallenLog("fallen_birch_log");
	public static final Block FALLEN_SPRUCE_LOG = registerFallenLog("fallen_spruce_log");
	public static final Block FALLEN_JUNGLE_LOG = registerFallenLog("fallen_jungle_log");
	public static final Block FALLEN_DARK_OAK_LOG = registerFallenLog("fallen_dark_oak_log");
	public static final Block FALLEN_ACACIA_LOG = registerFallenLog("fallen_acacia_log");
	public static final Block FALLEN_MANGROVE_LOG = registerFallenLog("fallen_mangrove_log");
	public static final Block FALLEN_CRIMSON_STEM = registerFallenLog("fallen_crimson_stem");
	public static final Block FALLEN_WARPED_STEM = registerFallenLog("fallen_warped_stem");
	public static final Block FALLEN_MUSHROOM_STEM = registerFallenLog("fallen_mushroom_stem");
	public static final Block FALLEN_CHERRY_LOG = registerFallenLog("fallen_cherry_log");

	public static final Block BLUE_FORGET_ME_NOT = registerForgetMeNot("blue_forget_me_not", CombinedStackingFlowerBlock.FlowerType.BLUE);
	public static final Block PURPLE_FORGET_ME_NOT = registerForgetMeNot("purple_forget_me_not", CombinedStackingFlowerBlock.FlowerType.PURPLE);
	public static final Block PINK_FORGET_ME_NOT = registerForgetMeNot("pink_forget_me_not", CombinedStackingFlowerBlock.FlowerType.PINK);
	public static final Block WHITE_FORGET_ME_NOT = registerForgetMeNot("white_forget_me_not", CombinedStackingFlowerBlock.FlowerType.WHITE);

	public static final Block BROWN_POLYPORE_MUSHROOM = registerPolypore("brown_polypore_mushroom");

	public static final Block SWEETSPIRE = register("sweetspire",() -> new TallFlowerBlock(Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
	public static final Block POTTED_SWEETSPIRE = register("potted_sweetspire", () -> new FlowerPotBlock(SWEETSPIRE, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final Block POTTED_BLUE_FORGET_ME_NOT = register("potted_blue_forget_me_not", () -> new FlowerPotBlock(BLUE_FORGET_ME_NOT, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final Block POTTED_PURPLE_FORGET_ME_NOT = register("potted_purple_forget_me_not", () -> new FlowerPotBlock(PURPLE_FORGET_ME_NOT, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final Block POTTED_PINK_FORGET_ME_NOT = register("potted_pink_forget_me_not", () -> new FlowerPotBlock(PINK_FORGET_ME_NOT, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
	public static final Block POTTED_WHITE_FORGET_ME_NOT = register("potted_white_forget_me_not", () ->new FlowerPotBlock(WHITE_FORGET_ME_NOT, Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

	public static final Block JAR_BLOCK = register("jar", MissingWildsBlocks::createJarBlock);
	public static final Block FIREFLY_JAR_BLOCK = register("firefly_jar", MissingWildsBlocks::createFireflyJarBlock);

	public static final Block TINTED_JAR_BLOCK = register("tinted_jar", MissingWildsBlocks::createJarBlock);
	public static final Block TINTED_FIREFLY_JAR_BLOCK = register("tinted_firefly_jar", () -> new FireflyJarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion()));

	public static final Block WHITE_STAINED_JAR_BLOCK = register("white_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block WHITE_STAINED_FIREFLY_JAR_BLOCK = register("white_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block ORANGE_STAINED_JAR_BLOCK = register("orange_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block ORANGE_STAINED_FIREFLY_JAR_BLOCK = register("orange_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block MAGENTA_STAINED_JAR_BLOCK = register("magenta_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block MAGENTA_STAINED_FIREFLY_JAR_BLOCK = register("magenta_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block LIGHT_BLUE_STAINED_JAR_BLOCK = register("light_blue_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK = register("light_blue_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block YELLOW_STAINED_JAR_BLOCK = register("yellow_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block YELLOW_STAINED_FIREFLY_JAR_BLOCK = register("yellow_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block LIME_STAINED_JAR_BLOCK = register("lime_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block LIME_STAINED_FIREFLY_JAR_BLOCK = register("lime_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block PINK_STAINED_JAR_BLOCK = register("pink_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block PINK_STAINED_FIREFLY_JAR_BLOCK = register("pink_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block GRAY_STAINED_JAR_BLOCK = register("gray_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block GRAY_STAINED_FIREFLY_JAR_BLOCK = register("gray_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block LIGHT_GRAY_STAINED_JAR_BLOCK = register("light_gray_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK = register("light_gray_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block CYAN_STAINED_JAR_BLOCK = register("cyan_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block CYAN_STAINED_FIREFLY_JAR_BLOCK = register("cyan_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block PURPLE_STAINED_JAR_BLOCK = register("purple_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block PURPLE_STAINED_FIREFLY_JAR_BLOCK = register("purple_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block BLUE_STAINED_JAR_BLOCK = register("blue_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block BLUE_STAINED_FIREFLY_JAR_BLOCK = register("blue_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block BROWN_STAINED_JAR_BLOCK = register("brown_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block BROWN_STAINED_FIREFLY_JAR_BLOCK = register("brown_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block GREEN_STAINED_JAR_BLOCK = register("green_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block GREEN_STAINED_FIREFLY_JAR_BLOCK = register("green_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block RED_STAINED_JAR_BLOCK = register("red_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block RED_STAINED_FIREFLY_JAR_BLOCK = register("red_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
	public static final Block BLACK_STAINED_JAR_BLOCK = register("black_stained_jar", MissingWildsBlocks::createJarBlock);
	public static final Block BLACK_STAINED_FIREFLY_JAR_BLOCK = register("black_stained_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);

	public static final Block FORGET_ME_NOT = register("forget_me_not", () -> new CombinedStackingFlowerBlock(MobEffects.LUCK, 10, Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE).pushReaction(PushReaction.DESTROY)));

	public static final Block FOOD_JAR_BLOCK = register("food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block TINTED_FOOD_JAR_BLOCK = register("tinted_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block WHITE_STAINED_FOOD_JAR_BLOCK = register("white_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block ORANGE_STAINED_FOOD_JAR_BLOCK = register("orange_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block MAGENTA_STAINED_FOOD_JAR_BLOCK = register("magenta_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block LIGHT_BLUE_STAINED_FOOD_JAR_BLOCK = register("light_blue_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block YELLOW_STAINED_FOOD_JAR_BLOCK = register("yellow_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block LIME_STAINED_FOOD_JAR_BLOCK = register("lime_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block PINK_STAINED_FOOD_JAR_BLOCK = register("pink_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block GRAY_STAINED_FOOD_JAR_BLOCK = register("gray_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block LIGHT_GRAY_STAINED_FOOD_JAR_BLOCK = register("light_gray_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block CYAN_STAINED_FOOD_JAR_BLOCK = register("cyan_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block PURPLE_STAINED_FOOD_JAR_BLOCK = register("purple_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block BLUE_STAINED_FOOD_JAR_BLOCK = register("blue_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block BROWN_STAINED_FOOD_JAR_BLOCK = register("brown_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block GREEN_STAINED_FOOD_JAR_BLOCK = register("green_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block RED_STAINED_FOOD_JAR_BLOCK = register("red_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);
	public static final Block BLACK_STAINED_FOOD_JAR_BLOCK = register("black_stained_food_jar", MissingWildsBlocks::createFoodJarBlock);

	public static final Block POTION_JAR_BLOCK = register("potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block TINTED_POTION_JAR_BLOCK = register("tinted_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block WHITE_STAINED_POTION_JAR_BLOCK = register("white_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block ORANGE_STAINED_POTION_JAR_BLOCK = register("orange_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block MAGENTA_STAINED_POTION_JAR_BLOCK = register("magenta_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block LIGHT_BLUE_STAINED_POTION_JAR_BLOCK = register("light_blue_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block YELLOW_STAINED_POTION_JAR_BLOCK = register("yellow_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block LIME_STAINED_POTION_JAR_BLOCK = register("lime_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block PINK_STAINED_POTION_JAR_BLOCK = register("pink_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block GRAY_STAINED_POTION_JAR_BLOCK = register("gray_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block LIGHT_GRAY_STAINED_POTION_JAR_BLOCK = register("light_gray_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block CYAN_STAINED_POTION_JAR_BLOCK = register("cyan_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block PURPLE_STAINED_POTION_JAR_BLOCK = register("purple_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block BLUE_STAINED_POTION_JAR_BLOCK = register("blue_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block BROWN_STAINED_POTION_JAR_BLOCK = register("brown_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block GREEN_STAINED_POTION_JAR_BLOCK = register("green_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block RED_STAINED_POTION_JAR_BLOCK = register("red_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);
	public static final Block BLACK_STAINED_POTION_JAR_BLOCK = register("black_stained_potion_jar", MissingWildsBlocks::createPotionJarBlock);

//	public static final Block WATERLILY_BLOCK = register("waterlily", () -> Services.PLATFORM.getWaterlilyBlock(Properties.copy(Blocks.LILY_PAD)));

	public static void init(BiConsumer<ResourceLocation,Block> registerFunction) {
		ModCompatHandler.getModCompats().forEach(modCompat -> {
			if (modCompat instanceof RegisteringModCompat) {
				((RegisteringModCompat) modCompat).registerBlocks();
			}
		});
		BLOCKS.forEach((id, block) -> registerFunction.accept(Constants.id(id), block));
	}

	public static Block register(String id, Supplier<? extends Block> supplier) {
		var block = supplier.get();
		BLOCKS.put(id, block);
		return block;
	}
	public static Block registerFallenLog(String id) {
		return register(id, () -> new FallenLogBlock(Properties.of().strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
	}
	public static Block registerFallenLogFromData(LogData logData, String modid) {
		var props = Properties.of().strength(2.0F).sound(SoundType.WOOD).noOcclusion();
		if (logData.light() > 0) {
			props.lightLevel((state) -> logData.light());
		}
		return register(modid + "_" + logData.name(), () -> new FallenLogBlock(props));
	}

	private static Block registerForgetMeNot(String id, CombinedStackingFlowerBlock.FlowerType type) {
		return register(id, () -> new StackingFlowerBlock(MobEffects.LUCK, 10, Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.NONE).pushReaction(PushReaction.DESTROY), type));
	}

	private static Block registerPolypore(String id) {
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
	public static Block createPotionJarBlock() {
		return new PotionJarBlock(Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion());
	}
}
