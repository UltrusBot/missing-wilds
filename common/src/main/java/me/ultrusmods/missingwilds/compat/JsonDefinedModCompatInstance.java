package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.data.JarData;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.data.ModCompatJsonData;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonDefinedModCompatInstance extends RegisteringModCompat {
    private final ModCompatJsonData modCompatJsonData;
    Map<LogData, Block> fallenLogBlocks = new HashMap<>();
    Map<JarData, Block> jarBlocks = new HashMap<>();
    Map<JarData, Block> fireflyJarBlocks = new HashMap<>();
    Map<JarData, Block> potionJarBlocks = new HashMap<>();
    

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
            Block block = MissingWildsBlocks.registerFallenLogFromData(logData, modCompatJsonData.modid());
            ModCompatHandler.addFallenLogBlock(Constants.id(modCompatJsonData.modid() + "_" + logData.name()), block);
            fallenLogBlocks.put(logData, block);
        });
        modCompatJsonData.jars().forEach(jarData -> {
            Block block = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_jar", MissingWildsBlocks::createJarBlock);
            Block foodJar = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_food_jar", MissingWildsBlocks::createFoodJarBlock);
            Block fireflyJar = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_firefly_jar", MissingWildsBlocks::createFireflyJarBlock);
            Block potionJar = MissingWildsBlocks.register(modCompatJsonData.modid() + "_" + jarData.name() + "_potion_jar", MissingWildsBlocks::createPotionJarBlock);
            ModCompatHandler.addJarBlock(jarData, block);
            ModCompatHandler.addFoodJarBlock(jarData, foodJar);
            ModCompatHandler.addFireflyJarBlock(jarData, fireflyJar);
            ModCompatHandler.addPotionJarBlock(jarData, potionJar);
            JarMaps.JAR_TO_FIREFLY_JAR.put(block, fireflyJar);
            JarMaps.JAR_TO_FOOD_JAR.put(block, foodJar);
            JarMaps.JAR_TO_POTION_JAR.put(block, potionJar);
            jarBlocks.put(jarData, block);
            fireflyJarBlocks.put(jarData, fireflyJar);
            potionJarBlocks.put(jarData, potionJar);
        });
    }
    public void registerItems() {
        fallenLogBlocks.forEach((logData, block) -> {
            Item item = MissingWildsItems.register(modCompatJsonData.modid() + "_" + logData.name(), block);
            ModCompatHandler.addFallenLogItem(item, logData);
        });
        jarBlocks.forEach((jarData, block) -> MissingWildsItems.register(modCompatJsonData.modid() + "_" + jarData.name() + "_jar", block));
        fireflyJarBlocks.forEach((jarData, block) -> MissingWildsItems.registerFireflyJar(modCompatJsonData.modid() + "_" + jarData.name() + "_firefly_jar", block));
        potionJarBlocks.forEach((jarData, block) -> MissingWildsItems.registerPotionJar(modCompatJsonData.modid() + "_" + jarData.name() + "_potion_jar", block));
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

public void generateData(ResourceAdder resourceAdder) {
    for (LogData logData : modCompatJsonData.logs()) {
        createFallenLogRecipes(resourceAdder, logData);
        createFallenLogAdvancement(resourceAdder, logData);
        createFallenLogLootTable(resourceAdder, logData);
    }
    for (JarData jarData : modCompatJsonData.jars()) {
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
                  "category": "building",
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
                    "id": "missingwilds:%1$s_%3$s"
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
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("loot_table/blocks/" + modid + "_" + logData.name() + ".json"), String.format("""
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
        String potionJarName = jarData.name() + "_potion_jar";
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + regularJarName + ".json"), getJarBlockstateJson(regularJarName, modid));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + fireflyJarName + ".json"), getJarBlockstateJson(fireflyJarName, modid));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + foodJarName + ".json"), getJarBlockstateJson(foodJarName, modid));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + modid + "_" + potionJarName + ".json"), getPotionJarBlockstateJson(potionJarName, modid));

        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + regularJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + fireflyJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + foodJarName + ".json"), getJarModelText(false, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + regularJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + fireflyJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + foodJarName + "_open.json"), getJarModelText(true, jarData));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + regularJarName + ".json"), getParentedModelText(modid + "_" + regularJarName));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + fireflyJarName + ".json"), getParentedModelText(modid + "_" + fireflyJarName));
        
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + "_one_third.json"), getPotionJarModelText(false, jarData, 1));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + "_two_thirds.json"), getPotionJarModelText(false, jarData, 2));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + ".json"), getPotionJarModelText(false, jarData, 3));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + "_one_third_open.json"), getPotionJarModelText(true, jarData, 1));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + "_two_thirds_open.json"), getPotionJarModelText(true, jarData, 2));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + modid + "_" + potionJarName + "_open.json"), getPotionJarModelText(true, jarData, 3));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + modid + "_" + potionJarName + ".json"), getParentedModelText(modid + "_" + potionJarName));
    }
    public void createJarRecipe(ResourceAdder resourceAdder, JarData data) {
        var id = Constants.id(modid + "_" + data.name() + "_jar");
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("recipe/" + modid + "_" + data.name() + "_jar" + ".json"),
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
                    "id": "%s"
                  },
                  "show_notification": true
                }
                """, data.blockId(), id));
    }
    public void createJarAdvancement(ResourceAdder resourceAdder, JarData data) {
        var recipeId = Constants.id(modid + "_" + data.name() + "_jar");
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("advancement/recipes/items/" + modid + "_" + data.name() + ".json"),
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
                                  "function": "minecraft:copy_custom_data",
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
    public static String getPotionJarModelText(boolean isOpen, JarData jarData, int level) {
        var open = isOpen ? "jar_open" : "jar";
        var parent = switch (level) {
            case 1 -> "missingwilds:block/template/one_third_potion_" + open + "_template";
            case 2 -> "missingwilds:block/template/two_thirds_potion_" + open + "_template";
            case 3 -> "missingwilds:block/template/potion_" + open + "_template";
            default -> throw new IllegalStateException("Unexpected level value: " + level);
        };
        return String.format("""
                {
                  "parent": "%s",
                  "textures": {
                    "glass": "%s",
                    "jar": "%s"
                  }
                }
                """, parent, jarData.blockTexture(), jarData.jarTexture());
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
        var logList = ModCompatHandler.FALLEN_LOG_BLOCKS.keySet().stream().toList();
        resourceAdder.addText(PackType.SERVER_DATA, Constants.id("tags/block/fallen_logs.json"), String.format("""
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
    public static String getPotionJarBlockstateJson(String id, String modId) {
        return String.format("""
                            {
                              "variants": {
                                "covered=false,level=1": {
                                  "model": "missingwilds:block/%2$s_%1$s_one_third_open"
                                },
                                "covered=false,level=2": {
                                  "model": "missingwilds:block/%2$s_%1$s_two_thirds_open"
                                },
                                "covered=false,level=3": {
                                  "model": "missingwilds:block/%2$s_%1$s_open"
                                },
                                "covered=true,level=1": {
                                  "model": "missingwilds:block/%2$s_%1$s_one_third"
                                },
                                "covered=true,level=2": {
                                  "model": "missingwilds:block/%2$s_%1$s_two_thirds"
                                },
                                "covered=true,level=3": {
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
