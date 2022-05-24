package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.block.PolyporeMushroomBlock;
import me.ultrusmods.missingwilds.block.StackingFlowerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MissingWildsBlocks {
	public static final Block FALLEN_OAK_LOG = registerFallenLog("fallen_oak_log");
	public static final Block FALLEN_BIRCH_LOG = registerFallenLog("fallen_birch_log");
	public static final Block FALLEN_SPRUCE_LOG = registerFallenLog("fallen_spruce_log");
	public static final Block FALLEN_JUNGLE_LOG = registerFallenLog("fallen_jungle_log");
	public static final Block FALLEN_DARK_OAK_LOG = registerFallenLog("fallen_dark_oak_log");
	public static final Block FALLEN_ACACIA_LOG = registerFallenLog("fallen_acacia_log");
	public static final Block FALLEN_CRIMSON_STEM = registerFallenLog("fallen_crimson_stem");
	public static final Block FALLEN_WARPED_STEM = registerFallenLog("fallen_warped_stem");
	public static final Block FALLEN_MUSHROOM_STEM = registerFallenLog("fallen_mushroom_stem");

	public static final Block BLUE_FORGET_ME_NOT = registerForgetMeNot("blue_forget_me_not");
	public static final Block PURPLE_FORGET_ME_NOT = registerForgetMeNot("purple_forget_me_not");
	public static final Block PINK_FORGET_ME_NOT = registerForgetMeNot("pink_forget_me_not");
	public static final Block WHITE_FORGET_ME_NOT = registerForgetMeNot("white_forget_me_not");

	public static final Block BROWN_POLYPORE_MUSHROOM = registerPolypore("brown_polypore_mushroom");

	public static final Block SWEETSPIRE = register(MissingWildsMod.id("sweetspire"), new TallFlowerBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block POTTED_SWEETSPIRE = register(MissingWildsMod.id("potted_sweetspire"), new FlowerPotBlock(SWEETSPIRE, FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block POTTED_BLUE_FORGET_ME_NOT = register(MissingWildsMod.id("potted_blue_forget_me_not"), new FlowerPotBlock(BLUE_FORGET_ME_NOT, FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block POTTED_PURPLE_FORGET_ME_NOT = register(MissingWildsMod.id("potted_purple_forget_me_not"), new FlowerPotBlock(PURPLE_FORGET_ME_NOT, FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block POTTED_PINK_FORGET_ME_NOT = register(MissingWildsMod.id("potted_pink_forget_me_not"), new FlowerPotBlock(PINK_FORGET_ME_NOT, FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block POTTED_WHITE_FORGET_ME_NOT = register(MissingWildsMod.id("potted_white_forget_me_not"), new FlowerPotBlock(WHITE_FORGET_ME_NOT, FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

	public static void init() {

	}

	private static Block register(Identifier id, Block block) {
		return Registry.register(Registry.BLOCK, id, block);
	}

	public static Block registerFallenLog(String id) {
		return register(MissingWildsMod.id(id), new FallenLogBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
	}

	private static Block registerForgetMeNot(String id) {
		return register(MissingWildsMod.id(id), new StackingFlowerBlock(StatusEffects.LUCK, 10, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	}

	private static Block registerPolypore(String id) {
		return register(MissingWildsMod.id(id), new PolyporeMushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.DIRT_BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
	}
}
