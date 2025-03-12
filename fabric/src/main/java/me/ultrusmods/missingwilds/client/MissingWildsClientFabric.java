package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.compat.ModCompatClient;
import me.ultrusmods.missingwilds.compat.bovines.BovinesAndButtercupsCompatClient;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.template.TemplateModCompatClient;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import me.ultrusmods.missingwilds.resource.MissingWildsAssetResources;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import static me.ultrusmods.missingwilds.client.MissingWildsClientCommon.CLIENT_COMPATS;

public class MissingWildsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if (Services.PLATFORM.isModLoaded("bovinesandbuttercups") && ModCompatHandler.isJsonModCompatEnabled()) {
            CLIENT_COMPATS.add(new BovinesAndButtercupsCompatClient());
        }
        if (ModCompatHandler.isJsonModCompatEnabled()) {
            MissingWildsAssetResources.init();
        }
        MissingWildsClientCommon.init();
        ParticleFactoryRegistry.getInstance().register(MissingWildsParticles.FIREFLY, FireflyParticle.Provider::new);
        EntityRendererRegistry.register(MissingWildsEntities.FIREFLY_SWARM, FireflySwarmRenderer::new);

        MissingWildsClientCommon.registerEntityRenderers(BlockEntityRenderers::register);
        MissingWildsClientCommon.registerItemColors(ColorProviderRegistry.ITEM::register);
        MissingWildsClientCommon.registerBlockColors(ColorProviderRegistry.BLOCK::register);

        if (Services.PLATFORM.isModLoaded("templates")) {
            TemplateModCompatClient.init();
        }

        CommonLifecycleEvents.TAGS_LOADED.register((registryAccess, client) -> {
            if (client) {
                CLIENT_COMPATS.forEach(ModCompatClient::onTagLoad);
            }
        });
    }


}
