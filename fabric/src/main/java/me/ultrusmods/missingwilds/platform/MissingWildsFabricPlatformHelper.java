package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class MissingWildsFabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }


    @Override
    public void setBlockRenderType(RenderType layer, Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(layer, blocks);
    }
}
