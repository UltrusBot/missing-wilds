package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

public class MissingWildsParticles {
    public static RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registry.PARTICLE_TYPE, Constants.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FIREFLY = PARTICLES.register("firefly", Services.PLATFORM::getParticleType);
    public static void init() {
    }
}
