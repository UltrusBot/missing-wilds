package me.ultrusmods.missingwilds.mixin;

import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GrassBlock.class)
public class GrassMixin {
    @SuppressWarnings("ExpectedReturnValue")
    @ModifyVariable(method = "performBonemeal", at = @At(value = "STORE", ordinal = 0))
    Holder missingWildsChangeBonemealFlowers(Holder value, ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if (serverLevel.getBiome(blockPos).is(MissingWildsTags.BIRCH)) {
            return MissingWildsConfiguredFeatures.FORGET_ME_NOT_BONEMEAL.value().config().feature();
        }
        return value;
    }
}
