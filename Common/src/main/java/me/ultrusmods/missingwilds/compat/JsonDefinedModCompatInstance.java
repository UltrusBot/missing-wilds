package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.data.JarData;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.data.ModCompatJsonData;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonDefinedModCompatInstance extends RegisteringModCompat {
    private final ModCompatJsonData modCompatJsonData;
    Map<LogData, RegistryObject<Block>> fallenLogBlocks = new HashMap<>();
    Map<JarData, RegistryObject<Block>> jarBlocks = new HashMap<>();
    Map<JarData, RegistryObject<Block>> fireflyJarBlocks = new HashMap<>();

    public JsonDefinedModCompatInstance(ModCompatJsonData modCompatJsonData) {
        super(modCompatJsonData.modid());
        this.modCompatJsonData = modCompatJsonData;
    }

    @Override
    public void init() {

    }

    @Override
    public void clientInit() {

    }

    public void registerBlocks() {
        modCompatJsonData.logs().forEach(logData -> {
            RegistryObject<Block> block = MissingWildsBlocks.registerFallenLogFromData(logData, modCompatJsonData.modid());
            Services.PLATFORM.getModCompatHandler().addFallenLogBlock(block);
            fallenLogBlocks.put(logData, block);
        });
        modCompatJsonData.jars().forEach(jarData -> {
            RegistryObject<Block> block = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_jar", MissingWildsBlocks::createJarBlock);
            RegistryObject<Block> foodJar = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_food_jar", MissingWildsBlocks::createFoodJarBlock);
            RegistryObject<Block> fireflyJar = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
            Services.PLATFORM.getModCompatHandler().addJarBlock(jarData, block);
            Services.PLATFORM.getModCompatHandler().addFoodJarBlock(jarData, foodJar);
            Services.PLATFORM.getModCompatHandler().addFireflyJarBlock(jarData, fireflyJar);
            JarMaps.JAR_TO_FIREFLY_JAR.put(block.get(), fireflyJar.get());
            JarMaps.JAR_TO_FOOD_JAR.put(block.get(), foodJar.get());
            jarBlocks.put(jarData, block);
            fireflyJarBlocks.put(jarData, fireflyJar);
        });
    }
    public void registerItems() {
        fallenLogBlocks.forEach((logData, block) -> {
            RegistryObject<Item> item = MissingWildsItems.register(modCompatJsonData.modid() + "_" + logData.name(), block);
            Services.PLATFORM.getModCompatHandler().addFallenLogItem(item, logData);
        });
        jarBlocks.forEach((jarData, block) -> MissingWildsItems.register(modCompatJsonData.modid() + "_" + jarData.name() + "_jar", block));
        fireflyJarBlocks.forEach((jarData, block) -> MissingWildsItems.registerFireflyJar(modCompatJsonData.modid() + "_" + jarData.name() + "_firefly_jar", block));
    }

    @Override
    public void registerBlockEntities() {
    }

    public ModCompatJsonData getModCompatJsonData() {
        return modCompatJsonData;
    }

    public void generateAssets(ResourceAdder resourceAdder) {
        for (LogData logData : modCompatJsonData.logs()) {
            createFallenLogBlockState(resourceAdder, logData);
            createFallenLogModels(resourceAdder, logData);
            createFallenLogRecipes(resourceAdder, logData);
            createFallenLogAdvancement(resourceAdder, logData);
            createFallenLogLootTable(resourceAdder, logData);
        }
        for (JarData jarData : modCompatJsonData.jars()) {
                createJarModels(resourceAdder, jarData);
                createJarRecipe(resourceAdder, jarData);
                createJarAdvancement(resourceAdder, jarData);
                createJarLootTables(resourceAdder, jarData);
        }
    }

    public void createFallenLogBlockState(ResourceAdder resourceAdder, LogData logData) {
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + logData.name() + ".json"), String.format("""
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
                """, logData.name(), modid));
    }

    public void createFallenLogModels(ResourceAdder resourceAdder, LogData logData) {
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + logData.name() + ".json"), getFallenLogModelText(logData, ""));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + logData.name() + "_mossy.json"), getFallenLogModelText(logData, "_mossy"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + logData.name() + "_snowy.json"), getFallenLogModelText(logData, "_snowy"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + logData.name() + ".json"), getParentedModelText(modid + "_" + logData.name()));
    }

    public void createFallenLogRecipes(ResourceAdder resourceAdder, LogData logData) {
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("recipes/" + modid + "_" + logData.name() + ".json"),
                String.format("""
                {
                  "type": "minecraft:crafting_shaped",
                  "group": "missingwilds:fallen_logs",
                  "key": {
                    "L": {
                      "item": "%2$s"
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
                """, modid, logData.blockId(), logData.name())
        );
    }
    public void createFallenLogAdvancement(ResourceAdder resourceAdder, LogData logData) {
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("advancements/recipes/items/" + modid + "_" + logData.name() + ".json"), String.format("""
                {
                  "parent": "minecraft:recipes/root",
                  "criteria": {
                    "has_log": {
                      "conditions": {
                        "items": [
                          {
                            "items": [
                              "%2$s"
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
                """, modid, logData.blockId(), logData.name()
        ));
    }
    public void createFallenLogLootTable(ResourceAdder resourceAdder, LogData logData) {
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("loot_tables/blocks/" + modid + "_" + logData.name() + ".json"), String.format("""
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
                """, modid, logData.name()));

    }
    public void createJarModels(ResourceAdder resourceAdder, JarData jarData) {
        String regularJarName = jarData.name() + "_jar";
        String fireflyJarName = jarData.name() + "_firefly_jar";
        String foodJarName = jarData.name() + "_food_jar";
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + regularJarName + ".json"), getJarBlockstateJson(regularJarName, modid));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + fireflyJarName + ".json"), getJarBlockstateJson(fireflyJarName, modid));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + foodJarName + ".json"), getJarBlockstateJson(foodJarName, modid));

        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + regularJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + fireflyJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + foodJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + regularJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + fireflyJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + foodJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + regularJarName + ".json"), getParentedModelText(modid + "_" + regularJarName));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + fireflyJarName + ".json"), getParentedModelText(modid + "_" + fireflyJarName));

    }
    public void createJarRecipe(ResourceAdder resourceAdder, JarData data) {
        var id = Constants.id(modid + "_" + data.name() + "_jar");
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("recipes/" + modid + "_" + data.name() + "_jar" + ".json"),
                String.format("""
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "misc",
                  "group": "missingwilds:glass_jars",
                  "key": {
                    "G": {
                      "item": "%s"
                    },
                    "P": {
                      "tag": "minecraft:planks"
                    }
                  },
                  "pattern": [
                    "GPG",
                    "G G",
                    "GGG"
                  ],
                  "result": {
                    "item": "%s"
                  },
                  "show_notification": true
                }
                """, data.blockId(), id));
    }
    public void createJarAdvancement(ResourceAdder resourceAdder, JarData data) {
        var recipeId = Constants.id(modid + "_" + data.name() + "_jar");
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("advancements/recipes/items/" + modid + "_" + data.name() + ".json"),
                String.format("""
                {
                  "parent": "minecraft:recipes/root",
                  "criteria": {
                    "has_glass": {
                      "conditions": {
                        "items": [
                          {
                            "items": [
                              "%1$s"
                            ]
                          }
                        ]
                      },
                      "trigger": "minecraft:inventory_changed"
                    },
                    "has_the_recipe": {
                      "conditions": {
                        "recipe": "%2$s"
                      },
                      "trigger": "minecraft:recipe_unlocked"
                    }
                  },
                  "requirements": [
                    [
                      "has_glass",
                      "has_the_recipe"
                    ]
                  ],
                  "rewards": {
                    "recipes": [
                      "%2$s"
                    ]
                  },
                  "sends_telemetry_event": false
                }
                """, data.blockId(), recipeId));
    }
    public void createJarLootTables(ResourceAdder resourceAdder, JarData jarData) {
        String jsonString = """
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
                          "name": "%s"
                        }
                      ],
                      "rolls": 1.0
                    }
                  ]
                }
                """;
        String regularJarName = jarData.name() + "_jar";
        String fireflyJarName = jarData.name() + "_firefly_jar";
        String foodJarName = jarData.name() + "_food_jar";

        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("loot_tables/blocks/" + modid + "_" + regularJarName + ".json"),
                String.format(jsonString, Constants.id(modid + "_" + regularJarName))
                );
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("loot_tables/blocks/" + modid + "_" + foodJarName + ".json"),
                String.format(jsonString, Constants.id(modid + "_" + regularJarName))
        );

        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("loot_tables/blocks/" + modid + "_" + fireflyJarName + ".json"),
                String.format("""
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
                                  "functions": [
                                    {
                                      "function": "minecraft:copy_name",
                                      "source": "block_entity"
                                    },
                                    {
                                      "block": "%1$s",
                                      "function": "minecraft:copy_state",
                                      "properties": [
                                        "light_level"
                                      ]
                                    },
                                    {
                                      "function": "minecraft:copy_nbt",
                                      "ops": [
                                        {
                                          "op": "replace",
                                          "source": "color",
                                          "target": "BlockEntityTag.color"
                                        }
                                      ],
                                      "source": "block_entity"
                                    }
                                  ],
                                  "name": "%1$s"
                                }
                              ],
                              "rolls": 1.0
                            }
                          ]
                        }
                        """, Constants.id(modid + "_" + fireflyJarName))
        );    }

    public static String getJarModelText(boolean isOpen, JarData jarData) {
        if (isOpen) {
            return String.format("""
                    {
                      "parent": "missingwilds:block/template/glass_jar_open_template",
                      "textures": {
                        "glass": "%s",
                        "jar": "%s"
                      }
                    }
                    """, jarData.blockTexture(), jarData.jarTexture());
        } else {
            return String.format("""
                    {
                      "parent": "missingwilds:block/template/glass_jar_template",
                      "textures": {
                        "glass": "%s",
                        "jar": "%s"
                      }
                    }
                    """, jarData.blockTexture(), jarData.jarTexture());
        }
    }

    public static String getFallenLogModelText(LogData data, String type) {
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
    private static String getParentedModelText(String blockModel) {
        return String.format("""
                {
                  "parent": "missingwilds:block/%s"
                }
                """, blockModel);
    }

    /**
     * Shouldn't be called in loop of all individual json defined mods, but at the end as this puts all the logs into the same tag
     */
    public static void generateFallenLogTags(ResourceAdder resourceAdder) {
        var logList = Services.PLATFORM.getModCompatHandler().getFallenLogBlocks().stream().map(RegistryObject::getId).toList();
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("tags/blocks/fallen_logs.json"), String.format("""
                {
                    "replace": false,
                    "values": [
                        %s
                    ]
                    }
                """, String.join(", ", logList.stream().map(log -> "\"" + log + "\"").toList())));
    }

    public static String getJarBlockstateJson(String id, String modId) {
        return String.format("""
                {
                  "variants": {
                    "covered=false": {
                      "model": "missingwilds:block/%2$s_%1$s_open"
                    },
                    "covered=true": {
                      "model": "missingwilds:block/%2$s_%1$s"
                    }
                  }
                }
                """, id, modId);
    }

    public interface ResourceAdder {
        void add(PackType type, ResourceLocation id, byte[] resource);

        default void addText(PackType type, ResourceLocation id, String text) {
            add(type, id, text.getBytes(StandardCharsets.UTF_8));
        }
    }


}
