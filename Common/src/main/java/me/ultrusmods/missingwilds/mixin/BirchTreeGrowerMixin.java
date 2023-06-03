package me.ultrusmods.missingwilds.mixin;

import me.ultrusmods.missingwilds.register.MissingWildsConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BirchTreeGrower.class)
public class BirchTreeGrowerMixin {
    @Inject(method = "getConfiguredFeature", at = @At("HEAD"), cancellable = true)
    public void addCustomBirchTrees$MissingWilds(RandomSource $$0, boolean hasBee, CallbackInfoReturnable<Holder<? extends ConfiguredFeature<?, ?>>> cir) {
        cir.setReturnValue(hasBee ? MissingWildsConfiguredFeatures.SAPLING_TREE_BEE : MissingWildsConfiguredFeatures.SAPLING_TREE);
    }
}
