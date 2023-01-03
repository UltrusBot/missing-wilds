package me.ultrusmods.missingwilds.entity;

import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

public class FireflySwarm extends AmbientCreature {
    private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(FireflySwarm.class, EntityDataSerializers.INT);

    public FireflySwarm(EntityType<? extends AmbientCreature> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SIZE, 1);
    }

    int getSize() {
        return this.entityData.get(SIZE);
    }

    @Override
    public void aiStep() {
        if (this.isAlive() && this.level.isDay()) {
            this.discard();
        }

        if (this.level.isClientSide() && this.random.nextInt(25) == 0) {
            for (int i = 0; i < this.getSize(); i++) {
                this.level.addParticle(MissingWildsParticles.FIREFLY.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
        super.aiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D);
    }
}
