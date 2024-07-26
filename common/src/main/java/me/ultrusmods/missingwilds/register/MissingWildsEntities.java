package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.entity.FireflySwarm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.BiConsumer;

public class MissingWildsEntities {
    public static final EntityType<FireflySwarm> FIREFLY_SWARM = EntityType.Builder.of(FireflySwarm::new, MobCategory.AMBIENT).sized(2.0F, 2.0F).clientTrackingRange(8).build("firefly_swarm");

    public static void register(BiConsumer<ResourceLocation, EntityType<?>> consumer) {
        consumer.accept(Constants.id("firefly_swarm"), FIREFLY_SWARM);

    }

}
