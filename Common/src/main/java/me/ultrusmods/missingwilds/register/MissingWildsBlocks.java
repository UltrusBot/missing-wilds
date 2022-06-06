package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.block.PolyporeMushroomBlock;
import me.ultrusmods.missingwilds.block.StackingFlowerBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class MissingWildsBlocks {
	public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registry.BLOCK, Constants.MOD_ID);

	public static final RegistryObject<Block> FALLEN_OAK_LOG = registerFallenLog("fallen_oak_log");
	public static final RegistryObject<Block> FALLEN_BIRCH_LOG = registerFallenLog("fallen_birch_log");
	public static final RegistryObject<Block> FALLEN_SPRUCE_LOG = registerFallenLog("fallen_spruce_log");
	public static final RegistryObject<Block> FALLEN_JUNGLE_LOG = registerFallenLog("fallen_jungle_log");
	public static final RegistryObject<Block> FALLEN_DARK_OAK_LOG = registerFallenLog("fallen_dark_oak_log");
	public static final RegistryObject<Block> FALLEN_ACACIA_LOG = registerFallenLog("fallen_acacia_log");
	public static final RegistryObject<Block> FALLEN_CRIMSON_STEM = registerFallenLog("fallen_crimson_stem");
	public static final RegistryObject<Block> FALLEN_WARPED_STEM = registerFallenLog("fallen_warped_stem");
	public static final RegistryObject<Block> FALLEN_MUSHROOM_STEM = registerFallenLog("fallen_mushroom_stem");

	public static final RegistryObject<Block> BLUE_FORGET_ME_NOT = registerForgetMeNot("blue_forget_me_not");
	public static final RegistryObject<Block> PURPLE_FORGET_ME_NOT = registerForgetMeNot("purple_forget_me_not");
	public static final RegistryObject<Block> PINK_FORGET_ME_NOT = registerForgetMeNot("pink_forget_me_not");
	public static final RegistryObject<Block> WHITE_FORGET_ME_NOT = registerForgetMeNot("white_forget_me_not");

	public static final RegistryObject<Block> BROWN_POLYPORE_MUSHROOM = registerPolypore("brown_polypore_mushroom");

	public static final RegistryObject<Block> SWEETSPIRE = register("sweetspire",() -> new TallFlowerBlock(Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_SWEETSPIRE = register("potted_sweetspire", () -> new FlowerPotBlock(SWEETSPIRE.get(), Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_BLUE_FORGET_ME_NOT = register("potted_blue_forget_me_not", () -> new FlowerPotBlock(BLUE_FORGET_ME_NOT.get(), Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_PURPLE_FORGET_ME_NOT = register("potted_purple_forget_me_not", () -> new FlowerPotBlock(PURPLE_FORGET_ME_NOT.get(), Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_PINK_FORGET_ME_NOT = register("potted_pink_forget_me_not", () -> new FlowerPotBlock(PINK_FORGET_ME_NOT.get(), Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POTTED_WHITE_FORGET_ME_NOT = register("potted_white_forget_me_not", () ->new FlowerPotBlock(WHITE_FORGET_ME_NOT.get(), Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

	public static void init() {
	}

	private static RegistryObject<Block> register(String id, Supplier<? extends Block> supplier) {
		return BLOCKS.register(id, supplier);
	}
	public static RegistryObject<Block> registerFallenLog(String id) {
		return register(id, () -> new FallenLogBlock(Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
	}

	private static RegistryObject<Block> registerForgetMeNot(String id) {
		return register(id, () -> new StackingFlowerBlock(MobEffects.LUCK, 10, Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	}

	private static RegistryObject<Block> registerPolypore(String id) {
		return register(id, () -> new PolyporeMushroomBlock(Properties.of(Material.WOOD, MaterialColor.DIRT).strength(0.2F).sound(SoundType.WOOD).noOcclusion()));
	}
}
