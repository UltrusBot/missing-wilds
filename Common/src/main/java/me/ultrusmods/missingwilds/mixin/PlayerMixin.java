package me.ultrusmods.missingwilds.mixin;

import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> $$0, Level $$1) {
        super($$0, $$1);
    }

    @Shadow public abstract void awardStat(ResourceLocation $$0, int $$1);

    @Inject(method = "checkMovementStatistics", at = @At("TAIL"))
    void addCrawlingStats(double dx, double dy, double dz, CallbackInfo ci) {
        if (!this.isPassenger()) {
            if (this.isVisuallyCrawling() && this.getFeetBlockState().is(MissingWildsTags.FALLEN_LOGS)) {
                int dist = Math.round((float)Math.sqrt(dx * dx + dz * dz) * 100.0F);
                this.awardStat(MissingWildsStats.LOG_CRAWL_ONE_CM, dist);
            }
        }
    }
}
