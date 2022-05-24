package me.ultrusmods.missingwilds.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(OverworldBiomeCreator.class)
public class OverworldBiomeCreatorMixin {
    @WrapWithCondition(method = "createNormalForest", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addForestFlowers(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V"))
    private static boolean disableForestFlowers$MissingWilds(GenerationSettings.Builder builder, boolean birch, boolean oldGrowth, boolean flower) {
        return !birch;
    }
    @WrapWithCondition(method = "createNormalForest", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addDefaultFlowers(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V"))
    private static boolean disableDefaultFlowers$MissingWilds(GenerationSettings.Builder builder, boolean birch, boolean oldGrowth, boolean flower) {
        return !birch;
    }
}
