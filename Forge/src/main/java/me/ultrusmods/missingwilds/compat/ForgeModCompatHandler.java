package me.ultrusmods.missingwilds.compat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.lukebemish.defaultresources.api.ResourceProvider;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.data.ModCompatJsonData;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ForgeModCompatHandler extends ModCompatHandler {
    private static final Gson GSON = new Gson();
    public void loadModCompat() {
        ResourceProvider.forceInitialization();

        var resourceLocations = ResourceProvider.instance().getResources(Constants.MOD_ID, "compat", predicate -> true);

        for (ResourceLocation resource : resourceLocations) {
            try (var resourceStream = ResourceProvider.instance().getResourceStreams(Constants.MOD_ID, resource)) {
                var optional = resourceStream.findFirst();
                if (optional.isPresent() && resource.getPath().endsWith(".json")) {
                    InputStream stream = optional.get();
                    JsonObject jsonObject = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
                    ModCompatJsonData modCompatJsonData = ModCompatJsonData.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow(false, Constants.LOG::error);
                    if (Services.PLATFORM.isModLoaded(modCompatJsonData.modid())) {
                        if (getModCompats().stream().anyMatch(modCompatInstance -> modCompatInstance.getModid().equals(modCompatJsonData.modid()))) {
                            // Should catch weird duplicate loading issue.
                            continue;
                        }
                        this.addModCompat(new JsonDefinedModCompatInstance(modCompatJsonData));
                    }
                }
            } catch (Exception e) {
                Constants.LOG.error("Failed to load mod compat file {} with error {}", resource, e.getMessage());
            }
        }
    }

    @Override
    public boolean isJsonModCompatEnabled() {
        return Services.PLATFORM.isModLoaded("dynamic_asset_generator");
    }
}
