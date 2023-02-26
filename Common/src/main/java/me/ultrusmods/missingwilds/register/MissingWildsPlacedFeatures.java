package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MissingWildsPlacedFeatures {

	public static final ResourceKey<PlacedFeature> PLACED_FALLEN_BIRCH_LOG = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "fallen_birch"));
	public static final ResourceKey<PlacedFeature> BLUE_FORGET_ME_NOT = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "blue_forget_me_not"));
	public static final ResourceKey<PlacedFeature> PURPLE_FORGET_ME_NOT = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "purple_forget_me_not"));
	public static final ResourceKey<PlacedFeature> PINK_FORGET_ME_NOT = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "pink_forget_me_not"));
	public static final ResourceKey<PlacedFeature> WHITE_FORGET_ME_NOT = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "white_forget_me_not"));
	public static final ResourceKey<PlacedFeature> SWEETSPIRE = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "sweetspire"));
	public static final ResourceKey<PlacedFeature> TALL_GRASS = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "tall_grass"));
	public static final ResourceKey<PlacedFeature> GRASS = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "grass"));
	public static final ResourceKey<PlacedFeature> TREES_BIRCH = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "trees_birch"));
	public static final ResourceKey<PlacedFeature> TALL_TREES_BIRCH = ResourceKey.create(BuiltinRegistries.PLACED_FEATURE.key(), new ResourceLocation(Constants.MOD_ID, "tall_trees_birch"));

	public static void init() {

	}
		public static final ResourceKey<PlacedFeature>[] FORGET_ME_NOTS = new ResourceKey[] { BLUE_FORGET_ME_NOT, PURPLE_FORGET_ME_NOT, PINK_FORGET_ME_NOT, WHITE_FORGET_ME_NOT };

}
