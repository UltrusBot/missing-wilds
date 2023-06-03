package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.MissingWildsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JarBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    public static final BooleanProperty COVERED = BooleanProperty.create("covered");

    public JarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(COVERED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COVERED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get())) {
            if (JarMaps.JAR_TO_FIREFLY_JAR.get(this) instanceof FireflyJarBlock jar) {
                level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)).setValue(FireflyJarBlock.LIGHT_LEVEL, 1));
                stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        } else if (FoodJarBlock.isValidItem(stack)) {
            if (JarMaps.JAR_TO_FOOD_JAR.get(this) instanceof FoodJarBlock jar) {
                level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)));
                FoodJarBlock.insertItem(level, pos, stack);
                return InteractionResult.SUCCESS;
            }
        }
        if (checkToggleCover(state, level, pos, player, hand)) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    static boolean checkToggleCover(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).isEmpty()) {
            if (state.getValue(COVERED)) {
                if (!level.isClientSide) {
                    level.playSound(null, pos, MissingWildsSounds.JAR_CLOSE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                level.setBlockAndUpdate(pos, state.setValue(COVERED, false));
            } else {
                if (!level.isClientSide) {
                    level.playSound(null, pos, MissingWildsSounds.JAR_OPEN.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                level.setBlockAndUpdate(pos, state.setValue(COVERED, true));
            }
            return true;
        }
        return false;
    }
}
