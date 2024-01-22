package me.ultrusmods.missingwilds.resource;

import dev.lukebemish.dynamicassetgenerator.api.ResourceCache;
import dev.lukebemish.dynamicassetgenerator.api.client.AssetResourceCache;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.server.packs.PackType;

import java.io.ByteArrayInputStream;

public class MissingWildsForgeAssetResources {
    public static final AssetResourceCache ASSET_CACHE = ResourceCache.register(new AssetResourceCache(Constants.id("assets")));

    public static void init() {
        Constants.LOG.info("Generating assets for Missing Wilds");
        for (ModCompatInstance modCompatInstance : Services.PLATFORM.getModCompatHandler().getModCompats()) {
            if (!(modCompatInstance instanceof JsonDefinedModCompatInstance modCompat)) continue;
            modCompat.generateAssets((type, id, resource) -> {
                if (type == PackType.CLIENT_RESOURCES) {
                    ASSET_CACHE.planSource(id, (outRl, context) -> () -> new ByteArrayInputStream(resource));
                }
            });
        }
    }
}
