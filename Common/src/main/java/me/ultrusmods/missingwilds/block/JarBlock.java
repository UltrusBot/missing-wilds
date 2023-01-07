package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.register.MissingWildsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
        if (player.isShiftKeyDown()) {
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
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
