package me.ultrusmods.missingwilds.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class FallenLogFeatureConfig implements FeatureConfiguration {
	public static final Codec<FallenLogFeatureConfig> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							BlockStateProvider.CODEC
									.fieldOf("state_provider").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.stateProvider)
					)
					.apply(instance, FallenLogFeatureConfig::new)
	);
	public final BlockStateProvider stateProvider;

	public FallenLogFeatureConfig(BlockStateProvider stateProvider) {
		this.stateProvider = stateProvider;
	}
}
