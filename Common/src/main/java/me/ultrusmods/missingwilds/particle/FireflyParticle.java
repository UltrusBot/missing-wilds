package me.ultrusmods.missingwilds.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;

public class FireflyParticle extends TextureSheetParticle {

    private final float speedMultiplier;
    private double xDir;
    private double yDir;
    private double zDir;
    private boolean lit;
    private boolean scale;

    protected FireflyParticle(ClientLevel level, double x, double y, double z, float r, float g, float b, int lifetime, float speedMultiplier, float scale) {
        super(level, x, y, z);
        this.lifetime = lifetime;
        this.speedMultiplier = speedMultiplier;
        this.xDir = (Math.random() * 2.0 - 1.0) * 0.02;
        this.yDir = (Math.random() * 2.0 - 1.0) * 0.02;
        this.zDir = (Math.random() * 2.0 - 1.0) * 0.02;
        this.xd = speedMultiplier != 0 ? this.xDir : 0;
        this.yd = speedMultiplier != 0 ? this.yDir : 0;
        this.zd = speedMultiplier != 0 ? this.zDir : 0;
        this.rCol = r;
        this.gCol = g;
        this.bCol = b;
        this.quadSize = 0.25f;
        this.lit = true;
        this.alpha = 0;
        this.scale(scale);
    }


    @Override
    public ParticleRenderType getRenderType() {
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
        // TODO: Make a better particle movement algorithm.
        this.xDir += (Math.random() * 2.0 - 1.0)  * speedMultiplier;
        this.yDir += (Math.random() * 2.0 - 1.0)  * speedMultiplier;
        this.zDir += (Math.random() * 2.0 - 1.0)  * speedMultiplier;
        if (speedMultiplier != 0) {
            this.xd = Mth.lerp(0.2F, this.xd, this.xDir);
            this.yd = Mth.lerp(0.2F, this.yd, this.yDir);
            this.zd = Mth.lerp(0.2F, this.zd, this.zDir);

        }
        if (Math.random() < 0.0005D) {
            this.lit = !this.lit;
        }
    }

    public int getLightColor(float $$0) {
        return this.lit ? 255 : 0;
    }


    public static class Provider implements ParticleProvider<FireflyParticleOptions> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(FireflyParticleOptions type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new FireflyParticle(level, x, y, z, type.getRed(), type.getGreen(), type.getBlue(), type.getLifetime(), type.getSpeedMultiplier(), type.getScale());
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
