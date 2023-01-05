package me.ultrusmods.missingwilds.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Locale;

public class FireflyParticleOptions implements ParticleOptions {

    public static final Codec<FireflyParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("r").forGetter((options) -> options.red),
            Codec.FLOAT.fieldOf("g").forGetter((options) -> options.green),
            Codec.FLOAT.fieldOf("b").forGetter((options) -> options.blue)
    ).apply(instance, FireflyParticleOptions::new));

        public static final ParticleOptions.Deserializer<FireflyParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
            public FireflyParticleOptions fromCommand(ParticleType<FireflyParticleOptions> particleType, StringReader reader) throws CommandSyntaxException {
                reader.expect(' ');
                float red = reader.readFloat();
                reader.expect(' ');
                float green = reader.readFloat();
                reader.expect(' ');
                float blue = reader.readFloat();
                return new FireflyParticleOptions(red, green, blue);
            }

            public FireflyParticleOptions fromNetwork(ParticleType<FireflyParticleOptions> particleType, FriendlyByteBuf buf) {
                float red = buf.readFloat();
                float green = buf.readFloat();
                float blue = buf.readFloat();
                return new FireflyParticleOptions(red, green, blue);
            }
        };
    private final float red;
    private final float green;
    private final float blue;

    public FireflyParticleOptions(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public FireflyParticleOptions(double red, double green, double blue) {
        this.red = (float) red;
        this.green = (float) green;
        this.blue = (float) blue;
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
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()), this.red, this.green, this.blue);
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

}
