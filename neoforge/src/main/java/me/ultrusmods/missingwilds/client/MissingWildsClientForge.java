package me.ultrusmods.missingwilds.client;


import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import me.ultrusmods.missingwilds.resource.MissingWildsAssetResources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MissingWildsClientForge {

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
//    @SubscribeEvent
//    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
////        event.register((blockState, blockAndTintGetter, blockPos, col) -> blockAndTintGetter != null && blockPos != null ? 2129968 : 7455580, MissingWildsBlocks.WATERLILY_BLOCK.get());
//    }
//
//    @SubscribeEvent
//    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
////        event.register((itemStack, col) -> 7455580, MissingWildsItems.WATERLILY_ITEM.get());
//    }


}
