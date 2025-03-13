package me.ultrusmods.missingwilds.client;


import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.compat.ModCompatClient;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.bovines.BovinesAndButtercupsCompatClient;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import me.ultrusmods.missingwilds.resource.MissingWildsAssetResources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

import static me.ultrusmods.missingwilds.client.MissingWildsClientCommon.CLIENT_COMPATS;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MissingWildsClientForge {
    static {
        if (Services.PLATFORM.isModLoaded("bovinesandbuttercups") && ModCompatHandler.isJsonModCompatEnabled()) {
            CLIENT_COMPATS.add(new BovinesAndButtercupsCompatClient());
        }
    }

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        
        MissingWildsClientCommon.init();
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MissingWildsParticles.FIREFLY, FireflyParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MissingWildsEntities.FIREFLY_SWARM, FireflySwarmRenderer::new);
        MissingWildsClientCommon.registerEntityRenderers(event::registerBlockEntityRenderer);
    }

    public static void initModCompatAssets() {
        if (ModCompatHandler.isJsonModCompatEnabled()) {
            MissingWildsAssetResources.init();
        }
    }

    static {
        initModCompatAssets();
    }
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        MissingWildsClientCommon.registerBlockColors(event::register);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        MissingWildsClientCommon.registerItemColors(event::register);
    }
}
