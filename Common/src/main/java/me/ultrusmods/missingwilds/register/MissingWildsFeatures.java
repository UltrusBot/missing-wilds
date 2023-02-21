package me.ultrusmods.missingwilds.register;

import com.mojang.serialization.Codec;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeature;
import me.ultrusmods.missingwilds.worldgen.feature.FallenLogFeatureConfig;
import me.ultrusmods.missingwilds.worldgen.feature.tree.BranchTreeDecorator;
import me.ultrusmods.missingwilds.worldgen.feature.tree.PolyporeMushroomTreeDecorator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MissingWildsFeatures {
	public static final RegistrationProvider<Feature<?>> FEATURES = RegistrationProvider.get(Registries.FEATURE, Constants.MOD_ID);
	public static final RegistrationProvider<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = RegistrationProvider.get(Registries.TREE_DECORATOR_TYPE, Constants.MOD_ID);

	public static final RegistryObject<Feature<FallenLogFeatureConfig>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(FallenLogFeatureConfig.CODEC));

	public static final RegistryObject<TreeDecoratorType<BranchTreeDecorator>> BRANCH_TREE = registerTreeDecorator("tree_branch", BranchTreeDecorator.CODEC);
	public static final RegistryObject<TreeDecoratorType<PolyporeMushroomTreeDecorator>> POLYPORE_MUSHROOM = registerTreeDecorator("polypore_tree", PolyporeMushroomTreeDecorator.CODEC);

	public static void init() { }



	private static <P extends TreeDecorator> RegistryObject<TreeDecoratorType<P>>  registerTreeDecorator(String id, Codec<P> codec) {
		return TREE_DECORATOR_TYPES.register(id, () -> new TreeDecoratorType<P>(codec));
	}
}
