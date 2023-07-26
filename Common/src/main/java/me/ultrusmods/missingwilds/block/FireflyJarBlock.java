package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get()) && state.getValue(LIGHT_LEVEL) < 15) {
            level.setBlockAndUpdate(pos, state.setValue(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL) + 1));
            stack.shrink(1);
            player.addItem(new ItemStack(Items.GLASS_BOTTLE));
            return InteractionResult.SUCCESS;
        }
        if (stack.is(Items.GLASS_BOTTLE) && state.getValue(LIGHT_LEVEL) > 1) {
            level.setBlockAndUpdate(pos, state.setValue(LIGHT_LEVEL, state.getValue(LIGHT_LEVEL) - 1));
            stack.shrink(1);
            player.addItem(new ItemStack(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get()));
            return InteractionResult.SUCCESS;
        } else if (stack.is(Items.GLASS_BOTTLE) && state.getValue(LIGHT_LEVEL) == 1) {
            Block block = JarMaps.JAR_TO_FIREFLY_JAR.inverse().get(this);
            if (block != null) {
                level.setBlockAndUpdate(pos, block.defaultBlockState());
                stack.shrink(1);
                player.addItem(new ItemStack(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get()));
                return InteractionResult.SUCCESS;
            }
        }
        if (player.isShiftKeyDown()) {
            ItemStack jarStack = new ItemStack(this.asItem());
            CompoundTag subTag = new CompoundTag();
            subTag.putString(LIGHT_LEVEL.getName(), String.valueOf(state.getValue(LIGHT_LEVEL)));
            jarStack.addTagElement("BlockStateTag", subTag);

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FireflyJarBlockEntity fireflyJarBlockEntity) {
                fireflyJarBlockEntity.saveToItem(jarStack);
                jarStack.setHoverName(fireflyJarBlockEntity.getName());
            }
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), jarStack));
            level.removeBlock(pos, false);
            return InteractionResult.SUCCESS;
        }
        if (JarBlock.checkToggleCover(state, level, pos, player, hand)) {
            return InteractionResult.SUCCESS;
        }
        if (player.getItemInHand(hand).getItem() instanceof DyeItem) {
            DyeColor color = ((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor();
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FireflyJarBlockEntity) {
                ((FireflyJarBlockEntity) blockEntity).mixColor(color.getTextureDiffuseColors());
                if (!player.isCreative()) {
                    player.getItemInHand(hand).shrink(1);
                }
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
                level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
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
        if (stack.hasCustomHoverName()) {
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
