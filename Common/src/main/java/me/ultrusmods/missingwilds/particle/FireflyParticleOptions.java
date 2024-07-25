package me.ultrusmods.missingwilds.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record FireflyParticleOptions(float red, float green, float blue, float scale, int lifetime, float speedMultiplier) implements ParticleOptions {


    public static final MapCodec<FireflyParticleOptions> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Codec.FLOAT.fieldOf("r").forGetter(FireflyParticleOptions::red),
            Codec.FLOAT.fieldOf("g").forGetter(FireflyParticleOptions::green),
            Codec.FLOAT.fieldOf("b").forGetter(FireflyParticleOptions::blue),
            Codec.FLOAT.fieldOf("scale").forGetter(FireflyParticleOptions::scale),
            Codec.INT.fieldOf("lifetime").forGetter(FireflyParticleOptions::lifetime),
            Codec.FLOAT.fieldOf("speedMultiplier").forGetter(FireflyParticleOptions::speedMultiplier)
    ).apply(instance, FireflyParticleOptions::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FireflyParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            FireflyParticleOptions::red,
            ByteBufCodecs.FLOAT,
            FireflyParticleOptions::green,
            ByteBufCodecs.FLOAT,
            FireflyParticleOptions::blue,
            ByteBufCodecs.FLOAT,
            FireflyParticleOptions::scale,
            ByteBufCodecs.INT,
            FireflyParticleOptions::lifetime,
            ByteBufCodecs.FLOAT,
            FireflyParticleOptions::speedMultiplier,
            FireflyParticleOptions::new
    );


    public FireflyParticleOptions(float red, float green, float blue, float scale, float speedMultiplier) {
        this(red, green, blue, scale, (int)(Math.random() * 120) + 180, speedMultiplier);
    }
    public FireflyParticleOptions(double red, double green, double blue, float scale, float speedMultiplier) {
        this((float) red, (float) green, (float) blue, scale, speedMultiplier);
    }



    @Override
    public ParticleType<FireflyParticleOptions> getType() {
        return MissingWildsParticles.FIREFLY;
    }

}
