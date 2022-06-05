package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.register.*;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MissingWildsMod implements ModInitializer {
	public static final String MOD_ID = "missingwilds";
	public static final List<Block> compatLogs = new ArrayList<>();
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TagKey<Block> FALLEN_LOGS = TagKey.of(Registry.BLOCK_KEY, id("fallen_logs"));
	public static final Predicate<BiomeSelectionContext> BIOMES = BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST);

	public static final ItemGroup MISSING_WILD_ITEMS = FabricItemGroupBuilder.create(
					id("items"))
			.icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG))
			.build();

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Missing Wilds is loading!");
		MissingWildsResources.init();
		MissingWildsBlocks.init();
		MissingWildsItems.init();
		MissingWildsFeatures.init();
		MissingWildsConfiguredFeatures.init();
		MissingWildsPlacedFeatures.init();
		ModCompat.checkModCompat();

		BiomeModification biomeModifications = BiomeModifications.create(new Identifier(MOD_ID, "world_features"));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PLACED_FALLEN_BIRCH_LOG.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.BLUE_FORGET_ME_NOT.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PURPLE_FORGET_ME_NOT.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.PINK_FORGET_ME_NOT.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.WHITE_FORGET_ME_NOT.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.SWEETSPIRE.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.TALL_GRASS.value()));
		biomeModifications.add(ModificationPhase.ADDITIONS, BIOMES, context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, MissingWildsPlacedFeatures.GRASS.value()));

		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_BIRCH_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_OAK_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_JUNGLE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_SPRUCE_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_DARK_OAK_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MissingWildsBlocks.FALLEN_ACACIA_LOG, 5, 5);
	}

}
