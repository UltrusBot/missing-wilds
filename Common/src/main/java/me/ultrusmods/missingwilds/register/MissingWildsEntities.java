package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;

public class MissingWildsEntities {
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registry.ENTITY_TYPE, Constants.MOD_ID);
    public static final RegistryObject<EntityType<FireflySwarm>> FIREFLY_SWARM = ENTITY_TYPES.register("firefly_swarm", Services.PLATFORM::createFirefly);

    public static void init() {

    }

}
