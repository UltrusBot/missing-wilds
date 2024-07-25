package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class MissingWildsNeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void setBlockRenderType(RenderType layer, Block... blocks) {
        for (Block block : blocks) {
            //TODO: Look into alternative for this with multiloader.
            ItemBlockRenderTypes.setRenderLayer(block, layer);
        }
    }
}