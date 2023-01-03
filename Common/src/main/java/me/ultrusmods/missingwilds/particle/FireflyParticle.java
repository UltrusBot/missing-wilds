package me.ultrusmods.missingwilds.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

// TODO, make this an advanced particle, with a color field.
public class FireflyParticle extends TextureSheetParticle {

    private double xDir;
    private double yDir;
    private double zDir;
    private boolean lit;

    protected FireflyParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        this.lifetime = (int)(Math.random() * 120) + 180;
        this.xDir = (Math.random() * 2.0 - 1.0) * 0.1;
        this.yDir = (Math.random() * 2.0 - 1.0) * 0.1;
        this.zDir = (Math.random() * 2.0 - 1.0) * 0.1;
        this.xd = this.xDir;
        this.yd = this.yDir;
        this.zd = this.zDir;
        this.rCol = .196f;
        this.gCol = .804f;
        this.bCol = .196f;
        this.quadSize = 0.25f;
        this.lit = true;
        this.alpha = 0;
    }

    public FireflyParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this(level, x, y, z);
        // Cursed, but it works so I don't have to make an advanced particle type. TODO: Make advanced particle type.
        if (xSpeed != 0 && ySpeed != 0 && zSpeed != 0) {
            this.rCol = (float) xSpeed;
            this.gCol = (float) ySpeed;
            this.bCol = (float) zSpeed;
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age >= this.lifetime - 40) {
            alpha -= this.random.nextFloat() * 0.1;
            this.alpha = Mth.clamp(this.alpha, 0, 1);
        } else if (alpha < 1 && this.age > 20) {
            alpha += this.random.nextFloat() * 0.1;
            this.alpha = Mth.clamp(this.alpha, 0, 1);
        }
        // Storing another direction isn't great, but it creates nice smooth movement. I should come back to this later.
        this.xDir += (Math.random() - Math.random()) * 0.02D;
        this.yDir += (Math.random() - Math.random()) * 0.02D;
        this.zDir += (Math.random() - Math.random()) * 0.02D;
        this.xd = Mth.lerp(0.2F, this.xd, this.xDir);
        this.yd = Mth.lerp(0.2F, this.yd, this.yDir);
        this.zd = Mth.lerp(0.2F, this.zd, this.zDir);

        if (Math.random() < 0.0005D) {
            this.lit = !this.lit;
        }
    }

    public int getLightColor(float $$0) {
        return this.lit ? 255 : 0;
    }


    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new FireflyParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
