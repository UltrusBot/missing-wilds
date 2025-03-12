package me.ultrusmods.missingwilds.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * For compat classes that add a new type of jar.
 */
public interface JarAddingCompat {
    
    Block getJarBlock();
    Item getJarItem(Block baseJar);
    String getJarSuffix();
    void addToJarMap(Block baseJar, Block customJar);
    boolean validJarInteraction(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult);
}
