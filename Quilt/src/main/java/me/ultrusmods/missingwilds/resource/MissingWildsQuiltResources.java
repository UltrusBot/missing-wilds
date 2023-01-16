package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.ModCompatQuilt;
import net.minecraft.SharedConstants;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.resource.loader.api.InMemoryResourcePack;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MissingWildsQuiltResources {

    public static void init() {

        ResourceLoader.get(PackType.CLIENT_RESOURCES)
                .registerResourcePackProfileProvider((profileAdder, factory) -> MissingWildsQuiltResources.providePacks(profileAdder, factory, PackType.CLIENT_RESOURCES));
        ResourceLoader.get(PackType.SERVER_DATA)
                .registerResourcePackProfileProvider((profileAdder, factory) -> MissingWildsQuiltResources.providePacks(profileAdder, factory, PackType.SERVER_DATA));
    }

    private static void providePacks(Consumer<Pack> profileAdder, Pack.PackConstructor packConstructor, PackType type) {
        var pack = new InMemoryResourcePack.Named("missingwildsCompat") {
            @Override
            public @NotNull ResourcePackActivationType getActivationType() {
                return ResourcePackActivationType.ALWAYS_ENABLED;
            }
        };
        System.out.println("Generating resource pack of type " + type.name());
        pack.putText("pack.mcmeta", String.format("""
                {"pack":{"pack_format":%d,"description":"MissingWilds Mod Compat Pack"}}
                	""", type.getVersion(SharedConstants.getCurrentVersion())));
        ArrayList<String> LOGS = new ArrayList<>();
        ModCompatQuilt.modCompats.forEach((modId, modCompat) -> {
            if (QuiltLoader.isModLoaded(modId)) {
                modCompat.addLogs();
                modCompat.getLogList().forEach(logData -> {
                    pack.putText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modId + "_" + logData.name() + ".json"), createBlockModel(logData, ""));
                    pack.putText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modId + "_" + logData.name() + "_mossy.json"), createBlockModel(logData, "_mossy"));
                    pack.putText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modId + "_" + logData.name() + "_snowy.json"), createBlockModel(logData, "_snowy"));
                    pack.putText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modId + "_" + logData.name() + ".json"), createBlockState(logData.name(), modId));
                    pack.putText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modId + "_" + logData.name() + ".json"), createLogItemModel(logData.name(), modId));
                    pack.putText(PackType.SERVER_DATA, Constants.id("recipes/" + modId + "_" + logData.name() + ".json"), createFallenLogRecipe(modId, logData));
                    pack.putText(PackType.SERVER_DATA, Constants.id("advancements/recipes/items/" + modId + "_" + logData.name() + ".json"), createFallenLogRecipeAdvancement(modId, logData));
                    pack.putText(PackType.SERVER_DATA, Constants.id("loot_tables/blocks/" + modId + "_" + logData.name() + ".json"), createFallenLogLootTable(modId, logData));
                    LOGS.add(modId + "_" + logData.name());
                });
            }
        });
        pack.putText(PackType.SERVER_DATA, Constants.id("tags/blocks/fallen_logs.json"), String.format("""
                {
                    "replace": false,
                    "values": [
                        %s
                    ]
                    }
                """, String.join(", ", LOGS.stream().map(log -> "\"" + Constants.id(log) + "\"").toList())));
        profileAdder.accept(Pack.create("missingWildsCompat", false, () -> pack, packConstructor,
                Pack.Position.TOP, PackSource.BUILT_IN));
    }

    private static String createLogItemModel(String name, String modId) {
        return String.format("""
                {
                  "parent": "missingwilds:block/%s_%s"
                }
                """, modId, name);
    }

    public static String createBlockState(String id, String modId) {
        return String.format("""
                {
                  "variants": {
                    "axis=x,cover=moss": {
                      "model": "missingwilds:block/%2$s_%1$s_mossy",
                      "y": 90
                    },
                    "axis=x,cover=none": {
                      "model": "missingwilds:block/%2$s_%1$s",
                      "y": 90
                    },
                    "axis=x,cover=snow": {
                      "model": "missingwilds:block/%2$s_%1$s_snowy",
                      "y": 90
                    },
                    "axis=y,cover=moss": {
                      "model": "missingwilds:block/%2$s_%1$s_mossy",
                      "x": 90
                    },
                    "axis=y,cover=none": {
                      "model": "missingwilds:block/%2$s_%1$s",
                      "x": 90
                    },
                    "axis=y,cover=snow": {
                      "model": "missingwilds:block/%2$s_%1$s_snowy",
                      "x": 90
                    },
                    "axis=z,cover=moss": {
                      "model": "missingwilds:block/%2$s_%1$s_mossy"
                    },
                    "axis=z,cover=none": {
                      "model": "missingwilds:block/%2$s_%1$s"
                    },
                    "axis=z,cover=snow": {
                      "model": "missingwilds:block/%2$s_%1$s_snowy"
                    }
                  }
                }
                """, id, modId);
    }

    public static String createBlockModel(ModCompatQuilt.LogData data, String type) {
        return String.format("""
                {
                  "parent": "missingwilds:block/template/fallen_log_template%s",
                  "textures": {
                    "log": "%s",
                    "log_inner": "%s"
                  }
                }
                """, type, data.logTexture(), data.strippedLogTexture());
    }

    public static String createFallenLogRecipe(String modId, ModCompatQuilt.LogData data) {
        return String.format("""
                {
                  "type": "minecraft:crafting_shaped",
                  "group": "missingwilds:fallen_logs",
                  "key": {
                    "L": {
                      "item": "%1$s:%2$s"
                    }
                  },
                  "pattern": [
                    "LLL",
                    "L L",
                    "LLL"
                  ],
                  "result": {
                    "count": 8,
                    "item": "missingwilds:%1$s_%3$s"
                  }
                }
                                                """, modId, data.name().substring(7), data.name());
    }

    public static String createFallenLogRecipeAdvancement(String modId, ModCompatQuilt.LogData data) {
        return String.format("""
                {
                  "parent": "minecraft:recipes/root",
                  "criteria": {
                    "has_log": {
                      "conditions": {
                        "items": [
                          {
                            "items": [
                              "%1$s:%2$s"
                            ]
                          }
                        ]
                      },
                      "trigger": "minecraft:inventory_changed"
                    },
                    "has_the_recipe": {
                      "conditions": {
                        "recipe": "missingwilds:%1$s_%3$s"
                      },
                      "trigger": "minecraft:recipe_unlocked"
                    }
                  },
                  "requirements": [
                    [
                      "has_log",
                      "has_the_recipe"
                    ]
                  ],
                  "rewards": {
                    "recipes": [
                      "missingwilds:%1$s_%3$s"
                    ]
                  }
                }
                """, modId, data.name().substring(7), data.name()
        );
    }

    public static String createFallenLogLootTable(String modId, ModCompatQuilt.LogData data) {
        return String.format("""
                {
                  "type": "minecraft:block",
                  "pools": [
                    {
                      "bonus_rolls": 0.0,
                      "conditions": [
                        {
                          "condition": "minecraft:survives_explosion"
                        }
                      ],
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "missingwilds:%s_%s"
                        }
                      ],
                      "rolls": 1.0
                    }
                  ]
                }
                """, modId, data.name());
    }
}
