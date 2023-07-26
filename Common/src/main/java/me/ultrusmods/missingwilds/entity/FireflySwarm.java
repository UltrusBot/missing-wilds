package me.ultrusmods.missingwilds.entity;

import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.item.FireflyJarItem;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.MissingWildsSounds;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class FireflySwarm extends PathfinderMob {
    private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(FireflySwarm.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> STILL = SynchedEntityData.defineId(FireflySwarm.class, EntityDataSerializers.BOOLEAN);
    private BlockPos nextPosition = null;
    private int waitTime = 0;
    private int pickNewPosTimer = 0;
    private boolean dayLightSafe = false;

    public FireflySwarm(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SIZE, 3);
        this.entityData.define(STILL, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag $$0) {
        super.addAdditionalSaveData($$0);
        $$0.putBoolean("DayLightSafe", this.dayLightSafe);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag $$0) {
        super.readAdditionalSaveData($$0);
        this.dayLightSafe = $$0.getBoolean("DayLightSafe");
    }

    public int getSize() {
        return this.entityData.get(SIZE);
    }

    public boolean isStill() {
        return this.entityData.get(STILL);
    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide() && this.isAlive() && this.level().isDay() && !dayLightSafe && this.random.nextInt(100) <= 10) {
            this.discard();
        }
        if (this.level().isClientSide()) {
            for (int i = 0; i < this.getSize() * 3; i++) {
                if (this.getRandom().nextInt(25) == 0) {
                    this.level().addParticle(new FireflyParticleOptions(.60f, .92f, .2f, 100, 0.0035f),
                            this.getX() + this.random.nextFloat() - 0.5f,
                            this.getY() + this.random.nextFloat(),
                            this.getZ() + this.random.nextFloat() - 0.5f,
                            0.0D, 0.0D, 0.0D);
                }
            }
        }
        super.aiStep();
    }

    @Override
    public EntityDimensions getDimensions(Pose $$0) {
        return super.getDimensions($$0).scale(isStill() ? 1 : 0.1f);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {

    }

    @Override
    public boolean attackable() {
        return false;
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
        pickNewPosTimer++;
        if ((this.nextPosition == null && this.waitTime <= 0) || pickNewPosTimer >= 200) {
            if (this.random.nextFloat() < 0.2) {
                this.waitTime = this.random.nextInt(200, 600); // 10 - 30 seconds
            } else {
                var pos = this.level().clip(new ClipContext(this.position(), this.position().add(this.random.nextInt(-8, 10), this.random.nextInt(-4, 6), this.random.nextInt(-8, 10)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
                if (!this.level().getBlockState(pos.getBlockPos()).isAir()) {
                    this.nextPosition = null;
                    this.setStill(true);
                } else {
                    this.nextPosition = pos.getBlockPos();
                    setStill(false);
                    pickNewPosTimer = 0;
                }
            }

        }
        if (this.nextPosition != null) {
            this.setDeltaMovement(this.getDeltaMovement().lerp(new Vec3(this.nextPosition.getX() + this.random.nextInt(-3, 4) - this.getX(), this.nextPosition.getY() - this.getY(), this.nextPosition.getZ() + this.random.nextInt(-3, 4) - this.getZ()).normalize().multiply(0.3f, 0.3f, 0.3f), 0.1));
        }

        if (this.nextPosition != null && this.distanceToSqr(this.nextPosition.getX(), this.nextPosition.getY(), this.nextPosition.getZ()) < 3) {
            this.nextPosition = null;
            setStill(true);
        }
        this.waitTime--;
    }

    void setStill(boolean still) {
        this.entityData.set(STILL, still);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> $$0) {
        if (STILL.equals($$0)) {
            refreshDimensions();
        }
        super.onSyncedDataUpdated($$0);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(Items.GLASS_BOTTLE)) {
            player.playSound(SoundEvents.BOTTLE_FILL);
            player.getItemInHand(hand).shrink(1);
            player.addItem(MissingWildsItems.FIREFLY_BOTTLE_ITEM.get().getDefaultInstance());
            shrink();
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if(stack.getItem() instanceof FireflyJarItem) {
            FireflyJarItem.increaseLightLevel(stack, 3);
            player.playSound(MissingWildsSounds.JAR_OPEN.get(), 1.0f, 1.0f);
            discard();
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if(stack.getItem() instanceof BlockItem blockItem && JarMaps.JAR_TO_FIREFLY_JAR.containsKey(blockItem.getBlock())) {
            player.playSound(MissingWildsSounds.JAR_OPEN.get(), 1.0f, 1.0f);
            var newStack = JarMaps.JAR_TO_FIREFLY_JAR.get(blockItem.getBlock()).asItem().getDefaultInstance();
            FireflyJarItem.increaseLightLevel(newStack, 3);
            player.setItemInHand(hand, newStack);
            discard();
            return InteractionResult.sidedSuccess(this.level().isClientSide);
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
                return random.nextFloat() < 0.7f && random.nextFloat() > level.getMoonBrightness();
            }
        }
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D);
    }
}
