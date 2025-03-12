package me.ultrusmods.missingwilds.compat.bovines;

import house.greenhouse.bovinesandbuttercups.content.component.BovinesDataComponents;
import house.greenhouse.bovinesandbuttercups.content.item.BovinesItems;
import me.ultrusmods.missingwilds.block.JarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class NectarJarBlock extends JarBlock implements EntityBlock {
    public static final IntegerProperty NECTAR_LEVEL = IntegerProperty.create("level", 1, 3);
    public NectarJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(COVERED, true).setValue(NECTAR_LEVEL, 1));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NECTAR_LEVEL);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof NectarJarBlockEntity nectarJarBlockEntity) {
            if (nectarJarBlockEntity.tryInsertNectar(stack, player)) {
                var count = nectarJarBlockEntity.getAmount();
                level.setBlock(pos, state.setValue(NECTAR_LEVEL, count), 3);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (stack.is(Items.BOWL)) {
                var nectar = nectarJarBlockEntity.getItemNectar();
                var bowl = new ItemStack(BovinesItems.NECTAR_BOWL);
                bowl.set(BovinesDataComponents.NECTAR, nectar);
                stack.consume(1, player);
                if (!player.getInventory().add(bowl)) {
                    player.drop(bowl, false);
                }
                var nectarLevel = nectarJarBlockEntity.removeNectarLevel();

                if (nectarLevel == 0) {
                    if (BovinesAndButtercupsModCompat.JAR_TO_NECTAR_JAR.inverse().get(this) instanceof JarBlock jar) {
                        level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)));
                    }
                } else {
                    level.setBlock(pos, state.setValue(NECTAR_LEVEL, nectarLevel), 3);
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack jarStack = new ItemStack(this.asItem());
        jarStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(NECTAR_LEVEL, state.getValue(NECTAR_LEVEL)));
        if (level.getBlockEntity(pos) instanceof NectarJarBlockEntity nectarJarBlockEntity) {
            var nectar = nectarJarBlockEntity.getItemNectar();
            if (nectar != null) {
                jarStack.set(BovinesDataComponents.NECTAR, nectar);
            }
        }
        return jarStack;
    }

    public static boolean setNectar(Level level, BlockPos pos, ItemStack stack) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof NectarJarBlockEntity nectarJarBlockEntity) {
            return nectarJarBlockEntity.setNectar(stack, 1);
        }
        return false;
    }
    
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NectarJarBlockEntity(blockPos, blockState);
    }
}
