package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsQuilt;
import me.ultrusmods.missingwilds.client.render.FireflySwarmRenderer;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsEntities;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class MissingWildsClientQuilt implements ClientModInitializer {

    @Override
    public void onInitializeClient(ModContainer mod) {
        MissingWildsClientCommon.init();
        MissingWildsQuilt.COMPAT_LOGS.forEach(block -> {
            BlockRenderLayerMap.put(RenderType.cutoutMipped(), block);
        });
        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register(((atlasTexture, registry) -> {
            registry.register(Constants.id("particle/firefly"));
        }));
        ParticleFactoryRegistry.getInstance().register(MissingWildsParticles.FIREFLY.get(), FireflyParticle.Provider::new);
        EntityRendererRegistry.register(MissingWildsEntities.FIREFLY_SWARM.get(), FireflySwarmRenderer::new);

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, col) -> blockAndTintGetter != null && blockPos != null ? 2129968 : 7455580, MissingWildsBlocks.WATERLILY_BLOCK.get());
        ColorProviderRegistry.ITEM.register((itemStack, col) -> 7455580, MissingWildsItems.WATERLILY_ITEM.get());

    }
}
