package me.ultrusmods.missingwilds.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Stats.class)
public interface CustomStatAccessor {
    @Invoker("makeCustomStat")
    static ResourceLocation makeCustomStat(String id, StatFormatter statFormatter) {
        throw new AssertionError();
    }
}
