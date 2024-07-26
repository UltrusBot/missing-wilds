package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
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

public class FireflyJarBlock extends JarBlock implements EntityBlock {
    public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("light_level", 1, 15);

    public FireflyJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIGHT_LEVEL, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIGHT_LEVEL);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.mayBuild()) {
            if (stack.is(MissingWildsItems.FIREFLY_BOTTLE_ITEM) && state.getValue(LIGHT_LEVEL) < 15) {
                level.setBlockAndUpdate(pos, state.setValue(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL) + 1));
                stack.shrink(1);
                player.addItem(new ItemStack(Items.GLASS_BOTTLE));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            if (stack.is(Items.GLASS_BOTTLE) && state.getValue(LIGHT_LEVEL) > 1) {
                level.setBlockAndUpdate(pos, state.setValue(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL) - 1));
                stack.shrink(1);
                player.addItem(new ItemStack(MissingWildsItems.FIREFLY_BOTTLE_ITEM));
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            } else if (stack.is(Items.GLASS_BOTTLE) && state.getValue(LIGHT_LEVEL) == 1) {
                Block block = JarMaps.JAR_TO_FIREFLY_JAR.inverse().get(this);
                if (block != null) {
                    level.setBlockAndUpdate(pos, block.defaultBlockState());
                    stack.shrink(1);
                    player.addItem(new ItemStack(MissingWildsItems.FIREFLY_BOTTLE_ITEM));
                    return ItemInteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        if (player.getItemInHand(hand).getItem() instanceof DyeItem) {
            DyeColor color = ((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor();
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FireflyJarBlockEntity) {
                ((FireflyJarBlockEntity) blockEntity).mixColor(color.getTextureDiffuseColor());
                if (!player.isCreative()) {
                    player.getItemInHand(hand).shrink(1);
                }
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
                level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (player.isShiftKeyDown()) {
            ItemStack jarStack = new ItemStack(this.asItem());
            jarStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL)));

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FireflyJarBlockEntity fireflyJarBlockEntity) {
                fireflyJarBlockEntity.saveToItem(jarStack, player.level().registryAccess());
                jarStack.set(DataComponents.CUSTOM_NAME, fireflyJarBlockEntity.getCustomName());
            }
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), jarStack));
            level.removeBlock(pos, false);
        } else {
            toggleCover(state, level, pos, player);
        }
        return InteractionResult.SUCCESS;

    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack jarStack = new ItemStack(this.asItem());
//        CompoundTag subTag = new CompoundTag();
//        subTag.putString(LIGHT_LEVEL.getName(), String.valueOf(state.getValue(LIGHT_LEVEL)));
//        jarStack.addTagElement("BlockStateTag", subTag);

        jarStack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY.with(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL)));


        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FireflyJarBlockEntity fireflyJarBlockEntity) {
            fireflyJarBlockEntity.saveToItem(jarStack, level.registryAccess());
            jarStack.set(DataComponents.CUSTOM_NAME, fireflyJarBlockEntity.getCustomName());
//            fireflyJarBlockEntity.saveToItem(jarStack);
//            jarStack.setHoverName(fireflyJarBlockEntity.getCustomName());
        }
        return jarStack;

    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        int lightLevel = blockState.getValue(LIGHT_LEVEL);
        if (lightLevel > 0 && random.nextInt(30 - lightLevel) == 0) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FireflyJarBlockEntity fireflyJarBlockEntity) {
                if (blockState.getValue(COVERED)) {
                    fireflyJarBlockEntity.createInnerParticles(level, lightLevel, pos, random);
                } else {
                    fireflyJarBlockEntity.createParticles(level, lightLevel, pos, random);
                }
            }

        }
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack stack) {
        if (stack.getComponents().has(DataComponents.CUSTOM_NAME)) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof FireflyJarBlockEntity fireflyJarBlockEntity) {
                fireflyJarBlockEntity.setCustomName(stack.getHoverName());
            }
        }

    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FireflyJarBlockEntity(pos, state);
    }
}
