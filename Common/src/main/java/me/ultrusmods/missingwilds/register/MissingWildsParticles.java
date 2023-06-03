package me.ultrusmods.missingwilds.register;

import com.mojang.serialization.Codec;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

public class MissingWildsParticles {
    public static RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, Constants.MOD_ID);

    public static final RegistryObject<ParticleType<FireflyParticleOptions>> FIREFLY = PARTICLES.register("firefly", () -> new ParticleType<>(true, FireflyParticleOptions.DESERIALIZER) {
        @Override
        public Codec<FireflyParticleOptions> codec() {
            return FireflyParticleOptions.CODEC;
        }
    });
    public static void init() {
    }
}
