package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class MissingWildsEntities {
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registry.ENTITY_TYPE, Constants.MOD_ID);
    public static final RegistryObject<EntityType<FireflySwarm>> FIREFLY_SWARM = ENTITY_TYPES.register("firefly_swarm", () -> EntityType.Builder.of(FireflySwarm::new, MobCategory.AMBIENT).sized(2.0F, 2.0F).build("firefly_swarm"));

    public static void init() {

    }

}
