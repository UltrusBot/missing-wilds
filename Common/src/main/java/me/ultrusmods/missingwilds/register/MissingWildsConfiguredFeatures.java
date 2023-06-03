package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;


public class MissingWildsConfiguredFeatures {
	static {
		MissingWildsFeatures.init();
	}
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_FALLEN_BIRCH_LOG = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "fallen_birch"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_BEES_0002 = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "birch_bees_0002"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> SUPER_BIRCH_BEES_0002 = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "super_birch_bees_0002"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_FORGET_ME_NOT = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "blue_forget_me_not"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_FORGET_ME_NOT = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "purple_forget_me_not"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_FORGET_ME_NOT = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "pink_forget_me_not"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_FORGET_ME_NOT = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "white_forget_me_not"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> SWEETSPIRE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "sweetspire"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "grass"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "tall_grass"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAPLING_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "sapling_tree"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAPLING_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "sapling_tree_bee"));

	public static void init() {
	}
}
