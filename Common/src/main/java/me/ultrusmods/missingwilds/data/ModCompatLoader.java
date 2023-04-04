package me.ultrusmods.missingwilds.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import io.github.lukebemish.defaultresources.api.ResourceProvider;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Loads the Mod Compatability json files using the Default Resources Library, see {@link ResourceProvider}
 */
public class ModCompatLoader {
    public static HashMap<String, ModCompat> modCompats = new HashMap<>();
    private static final Gson GSON = new Gson();

    public static void init() {
        ResourceProvider.forceInitialization();

        var resourceLocations = ResourceProvider.instance().getResources(Constants.MOD_ID, "compat", predicate -> true);

        for (ResourceLocation resource : resourceLocations) {
            try (var resourceStream = ResourceProvider.instance().getResourceStreams(Constants.MOD_ID, resource)) {
                var optional = resourceStream.findFirst();
                if (optional.isPresent() && resource.getPath().endsWith(".json")) {
                    InputStream stream = optional.get();
                    JsonObject jsonObject = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
                    ModCompat modCompat = ModCompat.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow(false, Constants.LOG::error);
                    if (Services.PLATFORM.isModLoaded(modCompat.modid())) {
                        modCompats.put(modCompat.modid(), modCompat);
                    }
                }
            } catch (Exception e) {
                Constants.LOG.error("Failed to load mod compat file {} with error {}", resource, e.getMessage());
            }
        }
    }

}
