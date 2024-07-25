package me.ultrusmods.missingwilds.register;

import com.mojang.serialization.MapCodec;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class MissingWildsParticles {


    public static final ParticleType<FireflyParticleOptions> FIREFLY = new ParticleType<>(true) {

        @Override
        public MapCodec<FireflyParticleOptions> codec() {
            return FireflyParticleOptions.CODEC;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, FireflyParticleOptions> streamCodec() {
            return FireflyParticleOptions.STREAM_CODEC;
        }
    };
    public static void register(BiConsumer<ResourceLocation, ParticleType<?>> particleConsumer) {
        particleConsumer.accept(Constants.id("firefly"), FIREFLY);
    }
}
