package me.ultrusmods.missingwilds.tags;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class MissingWildsTags {
    public static final TagKey<Block> FALLEN_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "fallen_logs"));
    public static final TagKey<Block> MOSS = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "moss"));
    public static final TagKey<Block> SNOW = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "snow"));
    public static final TagKey<Biome> BIRCH = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "birch"));
    public static final TagKey<Biome> OAK = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "oak"));
    public static final TagKey<Biome> SPRUCE = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "spruce"));
    public static final TagKey<Biome> JUNGLE = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "jungle"));
    public static final TagKey<Biome> ACACIA = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "acacia"));
    public static final TagKey<Biome> DARK_OAK = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "dark_oak"));
    public static final TagKey<Item> FORGET_ME_NOTS = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "forget_me_nots"));
    public static final TagKey<Biome> SPAWNS_FIREFLY_SWARMS = TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "spawns_firefly_swarms"));
    public static final TagKey<Item> FOOD_JAR_BLACKLIST = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "food_jar_blacklist"));
    /**
     * Items that can be put in a food jar, that typically aren't food.
     */
    public static final TagKey<Item> FOOD_JAR_OVERRIDE = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "food_jar_override"));

    public static final TagKey<Item> FIREFLY_JARS = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "firefly_jars"));
}
