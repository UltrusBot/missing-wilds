package me.ultrusmods.missingwilds.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/*
    Fabric & Forge don't play nice together, so this exists.
 */
@Mixin(FireBlock.class)
public interface FireBlockAccessor {
    @Invoker("setFlammable")
    void registerFlameable$MissingWilds(Block block, int encouragement, int flammability);
}
