package me.ultrusmods.missingwilds.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class FallenLogFeatureConfig implements FeatureConfig {
	public static final Codec<FallenLogFeatureConfig> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							BlockStateProvider.TYPE_CODEC
									.fieldOf("state_provider").forGetter(fallenLogFeatureConfig -> fallenLogFeatureConfig.stateProvider)
					)
					.apply(instance, FallenLogFeatureConfig::new)
	);
	public final BlockStateProvider stateProvider;

	public FallenLogFeatureConfig(BlockStateProvider stateProvider) {
		this.stateProvider = stateProvider;
	}
}
