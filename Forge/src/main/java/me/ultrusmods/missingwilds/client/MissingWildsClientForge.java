package me.ultrusmods.missingwilds.client;


import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsForge;
import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class MissingWildsClientForge {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        MissingWildsClientCommon.init();
        MissingWildsForge.COMPAT_LOGS.forEach(block -> {
            Services.PLATFORM.setBlockRenderType(RenderType.cutoutMipped(), block.get());
        });
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.register(MissingWildsParticles.FIREFLY.get(), FireflyParticle.Provider::new);
    }

    @SubscribeEvent
    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarmRenderer::new);
    }
}
