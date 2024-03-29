package me.ultrusmods.missingwilds.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class FireflyParticleOptions implements ParticleOptions {

    public static final Codec<FireflyParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("r").forGetter((options) -> options.red),
            Codec.FLOAT.fieldOf("g").forGetter((options) -> options.green),
            Codec.FLOAT.fieldOf("b").forGetter((options) -> options.blue),
            Codec.FLOAT.fieldOf("scale").forGetter((options) -> options.scale),
            Codec.FLOAT.fieldOf("speedMultiplier").forGetter((options) -> options.speedMultiplier)
    ).apply(instance, FireflyParticleOptions::new));

        public static final ParticleOptions.Deserializer<FireflyParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
            public FireflyParticleOptions fromCommand(ParticleType<FireflyParticleOptions> particleType, StringReader reader) throws CommandSyntaxException {
                reader.expect(' ');
                float red = reader.readFloat();
                reader.expect(' ');
                float green = reader.readFloat();
                reader.expect(' ');
                float blue = reader.readFloat();
                reader.expect(' ');
                float scale = reader.readFloat();
                reader.expect(' ');
                float speedMultiplier = reader.readFloat();
                return new FireflyParticleOptions(red, green, blue, scale, speedMultiplier);
            }

            public FireflyParticleOptions fromNetwork(ParticleType<FireflyParticleOptions> particleType, FriendlyByteBuf buf) {
                float red = buf.readFloat();
                float green = buf.readFloat();
                float blue = buf.readFloat();
                float scale = buf.readFloat();
                float speedMultiplier = buf.readFloat();
                return new FireflyParticleOptions(red, green, blue, scale, speedMultiplier);
            }
        };
    private final float red;
    private final float green;
    private final float blue;
    private final float scale;
    private final int lifetime;
    private final float speedMultiplier;


    public FireflyParticleOptions(float red, float green, float blue, float scale, float speedMultiplier) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.lifetime = (int)(Math.random() * 120) + 180;
        this.scale = scale;
        this.speedMultiplier = speedMultiplier;
    }
    public FireflyParticleOptions(double red, double green, double blue, float scale, float speedMultiplier) {
        this((float) red, (float) green, (float) blue, scale, speedMultiplier);
    }

    public FireflyParticleOptions(float red, float green, float blue, int lifetime, float speedMultiplier) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.lifetime = lifetime;
        this.speedMultiplier = speedMultiplier;
        this.scale = 1.0f;
    }


    @Override
    public ParticleType<FireflyParticleOptions> getType() {
        return MissingWildsParticles.FIREFLY.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeFloat(red);
        buffer.writeFloat(green);
        buffer.writeFloat(blue);
        buffer.writeFloat(scale);
        buffer.writeFloat(speedMultiplier);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f", BuiltInRegistries.PARTICLE_TYPE.getKey(this.getType()), this.red, this.green, this.blue);
    }

    public float getRed() {
        return red;
    }
    public float getGreen() {
        return green;
    }
    public float getBlue() {
        return blue;
    }
    public int getLifetime() {
        return lifetime;
    }
    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
    public float getScale() {
        return scale;
    }
}
