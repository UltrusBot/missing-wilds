package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FoodJarBlock extends JarBlock implements EntityBlock {
    public FoodJarBlock(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).isEmpty() && player.isShiftKeyDown()) {
            if (level.getBlockEntity(pos) instanceof FoodJarBlockEntity foodJarBlockEntity) {
                ItemStack stack = foodJarBlockEntity.removeItem();
                if (!stack.isEmpty()) {
                    player.addItem(stack);
                    if (foodJarBlockEntity.isEmpty()) {
                        if (JarMaps.JAR_TO_FOOD_JAR.inverse().get(this) instanceof JarBlock jar) {
                            level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (level.getBlockEntity(pos) instanceof FoodJarBlockEntity foodJarBlockEntity) {
            ItemStack stack = player.getItemInHand(hand);
            if (isValidItem(stack)) {
                boolean addItem = foodJarBlockEntity.addItems(player.getItemInHand(hand));
                if (addItem) {
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (checkToggleCover(state, level, pos, player, hand)) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof FoodJarBlockEntity foodJarBlockEntity) {
                foodJarBlockEntity.getItems().forEach(itemStack -> Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack));
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(state, level, blockPos, newState, moved);
        }
    }

    public static boolean isValidItem(ItemStack stack) {
        return !stack.is(MissingWildsTags.FOOD_JAR_BLACKLIST) && (stack.getItem().getFoodProperties() != null || stack.is(MissingWildsTags.FOOD_JAR_OVERRIDE));
    }

    public static boolean insertItem(Level level, BlockPos pos, ItemStack stack) {
        if (level.getBlockEntity(pos) instanceof FoodJarBlockEntity foodJarBlockEntity) {
            return foodJarBlockEntity.addItems(stack);
        }
        return false;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FoodJarBlockEntity(blockPos, blockState);
    }
}
