package me.ultrusmods.missingwilds.resource;

import dev.lukebemish.dynamicassetgenerator.api.DataResourceCache;
import dev.lukebemish.dynamicassetgenerator.api.ResourceCache;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.JsonAddingCompat;
import me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import net.minecraft.server.packs.PackType;

import java.io.ByteArrayInputStream;

public class MissingWildsDataResources {
    public static final DataResourceCache DATA_CACHE = ResourceCache.register(new DataResourceCache(Constants.id("data")));

    public static void init() {
        for (ModCompatInstance modCompatInstance : ModCompatHandler.getModCompats()) {
            if (!(modCompatInstance instanceof JsonDefinedModCompatInstance modCompat)) continue;
            modCompat.generateData((type, id, resource) -> {
                if (type == PackType.SERVER_DATA) {
                    DATA_CACHE.planSource(id, (outRl, context) -> () -> new ByteArrayInputStream(resource));
                }
            });
        }
        for (ModCompatInstance modCompatInstance : ModCompatHandler.getModCompats()) {
            if (!(modCompatInstance instanceof JsonAddingCompat modCompat)) continue;
            modCompat.addJson((type, id, resource) -> {
                if (type == PackType.SERVER_DATA) {
                    DATA_CACHE.planSource(id, (outRl, context) -> () -> new ByteArrayInputStream(resource));
                }
            });
        }
        JsonDefinedModCompatInstance.generateTags(((type, id, resource) -> {
            if (type == PackType.SERVER_DATA) {
                DATA_CACHE.planSource(id, (outRl, context) -> () -> new ByteArrayInputStream(resource));
            }
        }));

    }
}
