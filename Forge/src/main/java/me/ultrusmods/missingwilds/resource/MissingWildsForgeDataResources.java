package me.ultrusmods.missingwilds.resource;

import dev.lukebemish.dynamicassetgenerator.api.DataResourceCache;
import dev.lukebemish.dynamicassetgenerator.api.ResourceCache;
import dev.lukebemish.dynamicassetgenerator.api.client.AssetResourceCache;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.server.packs.PackType;

import java.io.ByteArrayInputStream;

public class MissingWildsForgeDataResources {
    public static final DataResourceCache DATA_CACHE = ResourceCache.register(new DataResourceCache(Constants.id("data")));

    public static void init() {
        for (ModCompatInstance modCompatInstance : Services.PLATFORM.getModCompatHandler().getModCompats()) {
            if (!(modCompatInstance instanceof JsonDefinedModCompatInstance modCompat)) continue;
            modCompat.generateAssets((type, id, resource) -> {
                if (type == PackType.SERVER_DATA) {
                    DATA_CACHE.planSource(id, (outRl, context) -> () -> new ByteArrayInputStream(resource));
                }
            });
        }
    }
}
