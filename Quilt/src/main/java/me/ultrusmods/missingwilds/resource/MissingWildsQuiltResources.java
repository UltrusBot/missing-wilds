package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.MissingWildsQuilt;
import me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance;
import me.ultrusmods.missingwilds.compat.ModCompatInstance;
import me.ultrusmods.missingwilds.compat.QuiltModCompatHandler;
import me.ultrusmods.missingwilds.data.LogData;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.resource.loader.api.InMemoryResourcePack;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import java.util.function.Consumer;

/**
 * Creates the virtual resource/data packs for mod compatability features added through {@link QuiltModCompatHandler}
 */
public class MissingWildsQuiltResources {

    public static void init() {

        ResourceLoader.get(PackType.CLIENT_RESOURCES)
                .registerResourcePackProfileProvider((profileAdder) -> MissingWildsQuiltResources.providePacks(profileAdder, PackType.CLIENT_RESOURCES));
        ResourceLoader.get(PackType.SERVER_DATA)
                .registerResourcePackProfileProvider((profileAdder) -> MissingWildsQuiltResources.providePacks(profileAdder, PackType.SERVER_DATA));
    }

    private static void providePacks(Consumer<Pack> profileAdder, PackType type) {
        var pack = new InMemoryResourcePack.Named("missingwildsCompat") {
            @Override
            public @NotNull ResourcePackActivationType getActivationType() {
                return ResourcePackActivationType.ALWAYS_ENABLED;
            }
        };
        pack.putText("pack.mcmeta", String.format("""
                {"pack":{"pack_format":%d,"description":"MissingWilds Mod Compat Pack"}}
                	""", SharedConstants.getCurrentVersion().getPackVersion(type)));
        for (ModCompatInstance modCompatInstance : MissingWildsQuilt.QUILT_MOD_COMPAT_HANDLER.getModCompats()) {
            if (!(modCompatInstance instanceof JsonDefinedModCompatInstance modCompat)) continue;
            String modId = modCompat.getModid();
            modCompat.generateAssets(pack::putResource);
            modCompat.getModCompatJsonData().logs().forEach(logData -> {
                if (QuiltLoader.isModLoaded("tablesaw")) {
                    pack.putText(PackType.SERVER_DATA, Constants.id("custom_recipes/tablesaw/" + modId + "_" + logData.name() + ".json"), createTablesawRecipe(modId, logData));
                }
            });
        }
        JsonDefinedModCompatInstance.generateFallenLogTags(pack::putResource);
        profileAdder.accept(Pack.readMetaAndCreate("missingWildsCompat", Component.literal("Missing Wilds Mod Compat"), false, name -> pack, type,
                Pack.Position.TOP, PackSource.DEFAULT));
    }
    public static String createTablesawRecipe(String modId, LogData data) {
        return String.format("""
                {
                    "input": "%2$s",
                    "result": "missingwilds:%1$s_%3$s"
                }
                """, modId, data.blockId(), data.name());
    }
}
