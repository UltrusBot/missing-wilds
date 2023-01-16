package me.ultrusmods.missingwilds.client.render;

import me.ultrusmods.missingwilds.entity.FireflySwarm;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FireflySwarmRenderer extends EntityRenderer<FireflySwarm> {

    public FireflySwarmRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(FireflySwarm entity) {
        return new ResourceLocation("missingwilds", "textures/particle/firefly.png");
    }
}
