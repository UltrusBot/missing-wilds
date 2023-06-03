package me.ultrusmods.missingwilds.mixin;

import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import me.ultrusmods.missingwilds.register.MissingWildsPlacedFeatures;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(GrassBlock.class)
public class GrassMixin {
    @SuppressWarnings("ExpectedReturnValue")
    @ModifyVariable(method = "performBonemeal", at = @At(value = "STORE", ordinal = 0))
    Optional<Holder.Reference<PlacedFeature>> missingWildsChangeBonemealFlowers(Optional<Holder.Reference<PlacedFeature>> value, ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if (serverLevel.getBiome(blockPos).is(MissingWildsTags.BIRCH)) {
            return serverLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(MissingWildsPlacedFeatures.FORGET_ME_NOT_BONEMEAL);
        }
        return value;
    }
}
