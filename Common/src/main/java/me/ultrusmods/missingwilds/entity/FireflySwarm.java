package me.ultrusmods.missingwilds.entity;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.item.FireflyJarItem;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.MissingWildsSounds;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class FireflySwarm extends PathfinderMob {
    private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(FireflySwarm.class, EntityDataSerializers.INT);
    private BlockPos nextPosition = null;
    private int waitTime = 0;

    public FireflySwarm(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SIZE, 3);
    }

    public int getSize() {
        return this.entityData.get(SIZE);
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide() && this.isAlive() && this.level.isDay() && this.random.nextInt(100) <= 10) {
            this.discard();
        }
        if (this.level.isClientSide()) {
            for (int i = 0; i < this.getSize() * 3; i++) {
                if (this.getRandom().nextInt(25) == 0) {
                    this.level.addParticle(new FireflyParticleOptions(.60f, .92f, .2f, 100, 0.0035f), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        }
        super.aiStep();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {

    }

    @Override
    protected void pushEntities() {

    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.0, 1.0));

    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.nextPosition == null && this.waitTime <= 0) {
            if (this.random.nextFloat() < 0.2) {
                this.waitTime = this.random.nextInt(100, 250);
            } else {
                this.nextPosition = this.blockPosition().offset(this.random.nextInt(-8, 8), this.random.nextInt(-4, 4), this.random.nextInt(-8, 8));
                if (!this.level.getBlockState(this.nextPosition).isAir()) {
                    this.nextPosition = null;
                }
            }

        }
        if (this.nextPosition != null) {
            this.setDeltaMovement(this.getDeltaMovement().lerp(new Vec3(this.nextPosition.getX() + this.random.nextInt(-3, 3) - this.getX(), this.nextPosition.getY() - this.getY(), this.nextPosition.getZ() + this.random.nextInt(-3, 3) - this.getZ()).normalize().multiply(0.5f, 0.5f, 0.5f), 0.1));
        }

        if (this.nextPosition != null && this.distanceToSqr(this.nextPosition.getX(), this.nextPosition.getY(), this.nextPosition.getZ()) < 3) {
            this.nextPosition = null;
        }
        this.waitTime--;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(Items.GLASS_BOTTLE)) {
            player.playSound(SoundEvents.BOTTLE_FILL);
            player.getItemInHand(hand).shrink(1);
            player.addItem(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get().getDefaultInstance());
            shrink();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if(stack.getItem() instanceof FireflyJarItem) {
            FireflyJarItem.increaseLightLevel(stack, 3);
            System.out.println("Increased light level of firefly jar");
            player.playSound(MissingWildsSounds.JAR_OPEN.get(), 1.0f, 1.0f);
            discard();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if(stack.getItem() instanceof BlockItem blockItem && JarMaps.JAR_TO_FIREFLY_JAR.containsKey(blockItem.getBlock())) {
            player.playSound(MissingWildsSounds.JAR_OPEN.get(), 1.0f, 1.0f);
            var newStack = JarMaps.JAR_TO_FIREFLY_JAR.get(blockItem.getBlock()).asItem().getDefaultInstance();
            FireflyJarItem.increaseLightLevel(newStack, 3);
            player.setItemInHand(hand, newStack);
            discard();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void shrink() {
        if (this.getSize() > 1) {
            this.entityData.set(SIZE, this.getSize() - 1);
        } else {
            this.discard();
        }
    }

    public static boolean checkFireflySpawnRules(EntityType<? extends FireflySwarm> entityType, LevelAccessor level, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource random) {
        if (level.getBiome(blockPos).is(MissingWildsTags.SPAWNS_FIREFLY_SWARMS)) {
            if (blockPos.getY() > 60 && blockPos.getY() < 70) {
                return random.nextFloat() < 0.6f && random.nextFloat() > level.getMoonBrightness();
            }
        }

        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D);
    }
}
