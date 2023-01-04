package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsFabric;
import me.ultrusmods.missingwilds.particle.FireflyParticle;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;

public class MissingWildsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MissingWildsClientCommon.init();
        MissingWildsFabric.COMPAT_LOGS.forEach(block -> {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutoutMipped());
        });
        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register(((atlasTexture, registry) -> {
            registry.register(Constants.id("particle/firefly"));
        }));
        ParticleFactoryRegistry.getInstance().register(MissingWildsParticles.FIREFLY.get(), FireflyParticle.Provider::new);
    }
}
