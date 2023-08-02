package me.ultrusmods.missingwilds.compat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.template.TemplateModCompat;
import me.ultrusmods.missingwilds.data.ModCompatJsonData;
import me.ultrusmods.missingwilds.platform.Services;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class FabricModCompatHandler extends ModCompatHandler {
    private static final Gson GSON = new Gson();

    @Override
    public void loadModCompat() {
        if (isJsonModCompatEnabled()) {
            ModContainer modContainer = FabricLoader.getInstance().getModContainer("missingwilds").orElseThrow();
            var folderPathOptional = modContainer.findPath("defaultresources/missingwilds/missingwilds/compat");
            try {
                if (folderPathOptional.isPresent()) {
                    var folderPath = folderPathOptional.get();
                    Files.list(folderPath).forEach(filePath -> {
                        try {
                            var fileName = filePath.getFileName().toString();
                            if (fileName.endsWith(".json")) {
                                InputStream stream = Files.newInputStream(filePath);
                                JsonObject jsonObject = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
                                var modCompatJsonData = ModCompatJsonData.CODEC.parse(JsonOps.INSTANCE, jsonObject).getOrThrow(false, Constants.LOG::error);
                                if (Services.PLATFORM.isModLoaded(modCompatJsonData.modid())) {
                                    this.addModCompat(new JsonDefinedModCompatInstance(modCompatJsonData));
                                }
                            }
                        } catch (Exception e) {
                            Constants.LOG.error("Failed to load mod compat file {} with error {}", filePath, e.getMessage());
                        }
                    });
                }
            } catch (Exception e) {
                Constants.LOG.error("Failed to load mod compat files with error {}", e.getMessage());
            }
        }
        if (Services.PLATFORM.isModLoaded("templates")) {
            this.addModCompat(new TemplateModCompat());
        }
    }

    @Override
    public boolean isJsonModCompatEnabled() {
        return FabricLoader.getInstance().isModLoaded("advanced_runtime_resource_pack");
    }
}
