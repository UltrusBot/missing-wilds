package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

public class MissingWildsEntities {
//    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);
//    public static final RegistryObject<EntityType<FireflySwarm>> FIREFLY_SWARM = ENTITY_TYPES.register("firefly_swarm", Services.PLATFORM::createFirefly);


    public static final EntityType<FireflySwarm> FIREFLY_SWARM = Services.PLATFORM.createFirefly();

    public static void register(BiConsumer<ResourceLocation, EntityType<?>> consumer) {
        consumer.accept(Constants.id("firefly_swarm"), FIREFLY_SWARM);

    }

}
