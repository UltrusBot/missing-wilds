package me.ultrusmods.missingwilds.block;

import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import me.ultrusmods.missingwilds.register.MissingWildsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class FireflyJarBlock extends JarBlock {
     public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("light_level", 1, 15);
     public static final BooleanProperty COVERED = BooleanProperty.create("covered");
     public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);

    public FireflyJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIGHT_LEVEL, 0).setValue(COVERED, true).setValue(COLOR, DyeColor.LIME));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL, COVERED, COLOR);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            ItemStack stack = new ItemStack(this.asItem());
            CompoundTag tag = new CompoundTag();
            tag.putInt("light_level", state.getValue(LIGHT_LEVEL));
            stack.setTag(tag);
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
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
        if (player.getItemInHand(hand).getItem() instanceof DyeItem) {
            DyeColor color = ((DyeItem) player.getItemInHand(hand).getItem()).getDyeColor();
            if (state.getValue(COLOR) != color) {
                level.setBlockAndUpdate(pos, state.setValue(COLOR, color));
                if (!level.isClientSide) {
                    level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                player.getItemInHand(hand).shrink(1);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        // FIrefly Spawn Strategy #1: Spawn a group of particles in an area, with an amount depending on the light level.
//        if (!blockState.getValue(COVERED) && blockState.getValue(LIGHT_LEVEL) > 0 && random.nextInt(50) == 0) {
//            // Use the light level to determine how many particles to spawn, and the area to spawn them in:
//            int lightLevel = blockState.getValue(LIGHT_LEVEL);
//            int particles = lightLevel * 3;
//            double radius = lightLevel / 2.0;
//            for (int i = 0; i < particles; i++) {
//                double x = pos.getX() + 0.5 + (radius * 2 * random.nextDouble() - radius);
//                double y = pos.getY() + 0.5 + (radius * 2 * random.nextDouble() - radius);
//                double z = pos.getZ() + 0.5 + (radius * 2 * random.nextDouble() - radius);
//
//                level.addParticle(MissingWildsParticles.FIREFLY.get(), x, y, z, 0.0D, 0.0D, 0.0D);
//            }
//
//        }

        // Firefly Spawn Strategy #2: Spawn a single particle in a small area above the jar, with a interval depending on the light level.
        int lightLevel = blockState.getValue(LIGHT_LEVEL);
        if (!blockState.getValue(COVERED) && lightLevel > 0 && random.nextInt(40 - lightLevel) == 0) {
            for (int i = 0; i < lightLevel / 2; i++) {
                double x = pos.getX() + 0.5 + (2 * random.nextDouble() - 1);
                double y = pos.getY() + 0.5 + (random.nextInt(3) + 1);
                double z = pos.getZ() + 0.5 + (2 * random.nextDouble() - 1);
                var color = blockState.getValue(COLOR).getTextureDiffuseColors();
                level.addParticle(MissingWildsParticles.FIREFLY.get(), x, y, z, color[0], color[1], color[2]);
            }
        }
    }
}
