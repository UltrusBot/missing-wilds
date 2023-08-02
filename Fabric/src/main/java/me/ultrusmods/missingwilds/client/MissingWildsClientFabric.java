package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class MissingWildsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MissingWildsClientCommon.init();
        ParticleFactoryRegistry.getInstance().register(MissingWildsParticles.FIREFLY.get(), FireflyParticle.Provider::new);
        EntityRendererRegistry.register(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarmRenderer::new);

        MissingWildsClientCommon.registerEntityRenderers(BlockEntityRenderers::register);
//        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, col) -> blockAndTintGetter != null && blockPos != null ? 2129968 : 7455580, MissingWildsBlocks.WATERLILY_BLOCK.get());
//        ColorProviderRegistry.ITEM.register((itemStack, col) -> 7455580, MissingWildsItems.WATERLILY_ITEM.get());
    }


}
