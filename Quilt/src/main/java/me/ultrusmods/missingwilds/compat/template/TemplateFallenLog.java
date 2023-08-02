package me.ultrusmods.missingwilds.compat.template;

import com.google.common.base.MoreObjects;
import io.github.cottonmc.templates.Templates;
import io.github.cottonmc.templates.api.TemplateInteractionUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TemplateFallenLog extends SimpleFallenLogBlock implements EntityBlock {
    public TemplateFallenLog(Properties settings) {
        super(settings);
        registerDefaultState(TemplateInteractionUtil.setDefaultStates(defaultBlockState()));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return Templates.TEMPLATE_BLOCK_ENTITY.create(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(TemplateInteractionUtil.appendProperties(builder));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        InteractionResult result = TemplateInteractionUtil.onUse(blockState, level, blockPos, player, interactionHand, blockHitResult);
        if (!result.consumesAction()) result = super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        return result;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        TemplateInteractionUtil.onStateReplaced(blockState, level, blockPos, blockState2, bl);
        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        TemplateInteractionUtil.onPlaced(level, blockPos, blockState, livingEntity, itemStack);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return MoreObjects.firstNonNull(TemplateInteractionUtil.getCollisionShape(blockState, blockGetter, blockPos, collisionContext), super.getCollisionShape(blockState, blockGetter, blockPos, collisionContext));
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return TemplateInteractionUtil.emitsRedstonePower(blockState);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return TemplateInteractionUtil.getWeakRedstonePower(blockState, blockGetter, blockPos, direction);
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return TemplateInteractionUtil.getStrongRedstonePower(blockState, blockGetter, blockPos, direction);
    }
}
