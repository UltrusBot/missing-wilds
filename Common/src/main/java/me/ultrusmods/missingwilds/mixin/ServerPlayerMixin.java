package me.ultrusmods.missingwilds.mixin;

import com.mojang.authlib.GameProfile;
import me.ultrusmods.missingwilds.stat.MissingWildsStats;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {

    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @Inject(method = "checkMovementStatistics", at = @At("TAIL"))
    void addCrawlingStats(double dx, double dy, double dz, CallbackInfo ci) {
        if (!this.isPassenger()) {
            if (this.isVisuallyCrawling() && this.getBlockStateOn().is(MissingWildsTags.FALLEN_LOGS)) {
                int dist = Math.round((float)Math.sqrt(dx * dx + dz * dz) * 100.0F);
                this.awardStat(MissingWildsStats.LOG_CRAWL_ONE_CM, dist);
            }
        }
    }
}
