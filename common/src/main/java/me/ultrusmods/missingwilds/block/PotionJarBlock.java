package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.PotionJarBlockEntity;
import me.ultrusmods.missingwilds.register.MissingWildsDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
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

public class PotionJarBlock extends JarBlock implements EntityBlock {
    public static final IntegerProperty POTION_LEVEL = IntegerProperty.create("level", 1, 3);
    public PotionJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(COVERED, true).setValue(POTION_LEVEL, 1));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POTION_LEVEL);
    }
    

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PotionJarBlockEntity potionJarBlockEntity) {
            if (potionJarBlockEntity.tryInsertPotion(stack, player)) {
                var count = potionJarBlockEntity.getAmount();
                level.setBlock(pos, state.setValue(POTION_LEVEL, count), 3);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (stack.is(Items.GLASS_BOTTLE)) {
                var potion = potionJarBlockEntity.getPotion();
                var newPotion = PotionContents.createItemStack(Items.POTION, potion);
                stack.consume(1, player);
                if (!player.getInventory().add(newPotion)) {
                    player.drop(newPotion, false);
                }
                var potionLevel = potionJarBlockEntity.removePotionLevel();
                level.setBlock(pos, state.setValue(POTION_LEVEL, potionLevel), 3);
                if (potionLevel == 0) {
                    if (JarMaps.JAR_TO_POTION_JAR.inverse().get(this) instanceof JarBlock jar) {
                        level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)));
                    }
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack jarStack = new ItemStack(this.asItem());
        jarStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(POTION_LEVEL, state.getValue(POTION_LEVEL)));
        if (level.getBlockEntity(pos) instanceof PotionJarBlockEntity potionJarBlockEntity) {
            var potion = potionJarBlockEntity.getPotion();
            if (potion != null) {
                jarStack.set(MissingWildsDataComponents.POTION, potion);
            }
        }
        return jarStack;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PotionJarBlockEntity(blockPos, blockState);
    }

    public static boolean setPotion(Level level, BlockPos pos, ItemStack stack) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PotionJarBlockEntity potionJarBlockEntity) {
            return potionJarBlockEntity.setPotion(stack, 1);
        }
        return false;
    }
}
