package me.ultrusmods.missingwilds.register;

import com.mojang.serialization.Codec;
import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeature;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import me.ultrusmods.missingwilds.worldgen.feature.tree.PolyporeMushroomTreeDecorator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class MissingWildsFeatures {
	public static final Feature<FallenLogFeatureConfig> FALLEN_LOG = registerFeature("fallen_log", new FallenLogFeature(FallenLogFeatureConfig.CODEC));

	public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE = registerTreeDecorator("tree_branch", BranchTreeDecorator.CODEC);
	public static final TreeDecoratorType<PolyporeMushroomTreeDecorator> POLYPORE_MUSHROOM = registerTreeDecorator("polypore_tree", PolyporeMushroomTreeDecorator.CODEC);

	public static void init() { }

	private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
		return Registry.register(Registry.FEATURE, MissingWildsMod.id(name), feature);
	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, Codec<P> codec) {
		return Registry.register(Registry.TREE_DECORATOR_TYPE, id, new TreeDecoratorType<P>(codec));
	}
}
