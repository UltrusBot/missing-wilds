package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeature;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import me.ultrusmods.missingwilds.worldgen.feature.tree.PolyporeMushroomTreeDecorator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.BiConsumer;

public class MissingWildsFeatures {

	public static final Feature<FallenLogFeatureConfig> FALLEN_LOG_CONFIG = new FallenLogFeature(FallenLogFeatureConfig.CODEC);

	public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR = new TreeDecoratorType<>(BranchTreeDecorator.CODEC);
	public static final TreeDecoratorType<PolyporeMushroomTreeDecorator> POLYPORE_MUSHROOM_TREE_DECORATOR = new TreeDecoratorType<>(PolyporeMushroomTreeDecorator.CODEC);

	public static void registerFeature(BiConsumer<ResourceLocation, Feature<?>> registerFunction) {
		registerFunction.accept(Constants.id("fallen_log"), FALLEN_LOG_CONFIG);

	}
	public static void registerTreeDecorator(BiConsumer<ResourceLocation, TreeDecoratorType<?>> registerFunction) {
		registerFunction.accept(Constants.id("tree_branch"), BRANCH_TREE_DECORATOR);
		registerFunction.accept(Constants.id("polypore_tree"), POLYPORE_MUSHROOM_TREE_DECORATOR);

	}
}
