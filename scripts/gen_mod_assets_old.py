#
# NOTE: This is version of the mod asset generator was to make forge & fabric, but I decided to stick with ARRP for fabric.
#


# import json
# import os
#
# modCompatPath = "./mod_compat"
# modCompatFiles = os.listdir(modCompatPath)
# BLOCKSTATE = open("./templates/blockstate.json").read()
# BLOCK_MODEL = open("./templates/block_model.json").read()
# BLOCK_MODEL_MOSSY = open("./templates/block_model_mossy.json").read()
# ITEM_MODEL = open("./templates/item_model.json").read()
#
# RECIPE_FABRIC = open("./templates/recipe_fabric.json").read()
# RECIPE_FORGE = open("./templates/recipe_forge.json").read()
#
# LOOTTABLE = open("./templates/loot_table.json").read()
#
# def getTexturePath(modName: str, log: str, isStripped: bool):
#     if modName == "promenade":
#         if not isStripped:
#             return f"{modName}:block/{log}/side"
#         else:
#             return f"{modName}:block/stripped_{log}/side"
#
#     if not isStripped:
#         return f"{modName}:block/{log}"
#     else:
#         return f"{modName}:block/stripped_{log}"
#
# def generateBlockstate(modName, loader, log, common):
#     if (common):
#         loader = "Common"
#     blockState = BLOCKSTATE.replace("MOD", modName).replace("LOG", log)
#     with open(f"../{loader}/src/main/resources/assets/missingwilds/blockstates/{modName}_fallen_{log}.json", "w") as outfile:
#         outfile.write(blockState)
#
# def generateBlockModels(modName, loader, log, common):
#     if (common):
#         loader = "Common"
#     BlocKModel = BLOCK_MODEL.replace("LOG_PATH", getTexturePath(modName, log, False)).replace("STRIPPED_PATH", getTexturePath(modName, log, True))
#     with open(f"../{loader}/src/main/resources/assets/missingwilds/models/block/{modName}_fallen_{log}.json", "w") as outfile:
#         outfile.write(BlocKModel)
#     BlocKModel = BLOCK_MODEL_MOSSY.replace("LOG_PATH", getTexturePath(modName, log, False)).replace("STRIPPED_PATH", getTexturePath(modName, log, True))
#     with open(f"../{loader}/src/main/resources/assets/missingwilds/models/block/{modName}_fallen_{log}_mossy.json", "w") as outfile:
#         outfile.write(BlocKModel)
#
#     ItemModel = ITEM_MODEL.replace("LOG", log).replace("MOD", modName)
#     with open(f"../{loader}/src/main/resources/assets/missingwilds/models/item/{modName}_fallen_{log}.json", "w") as outfile:
#         outfile.write(ItemModel)
#
# def generateRecipes(modName, loader, log):
#     if loader == "Forge":
#         with open(f"../{loader}/src/main/resources/data/missingwilds/recipes/{modName}_fallen_{log}.json", "w") as outfile:
#             outfile.write(RECIPE_FORGE.replace("LOG", log).replace("MOD", modName))
#     if loader == "Fabric":
#         with open(f"../{loader}/src/main/resources/data/missingwilds/recipes/{modName}_fallen_{log}.json", "w") as outfile:
#             outfile.write(RECIPE_FABRIC.replace("LOG", log).replace("MOD", modName))
#
# def generateLoottables(modName, loader, log, common):
#     if (common):
#         loader = "Common"
#     with open(f"../{loader}/src/main/resources/data/missingwilds/loot_tables/{modName}_{log}.json", "w") as outfile:
#         outfile.write(LOOTTABLE.replace("LOG", log).replace("MOD", modName))
#
# def generateModJsons(modName, loader, logs, common):
#     for log in logs:
#         generateBlockstate(modName, loader, log, common)
#         generateBlockModels(modName, loader, log, common)
#         generateRecipes(modName, loader, log)
#         generateLoottables(modName, loader, log, common)
#
# def handleModCompat(modData):
#     loader = ""
#     modName = ""
#     logs = []
#
#     for key in modData:
#         if key == "loader":
#             loader = modData["loader"]
#         if key == "id":
#             modName = modData["id"]
#         if key == "logs":
#             logs = modData["logs"]
#
#     common = loader == "both" # Used to store certain jsons in common, rather than in the mod side.
#     if loader == "fabric" or loader == "both":
#         generateModJsons(modName, "Fabric", logs, common)
#     if loader == "forge" or loader == "both":
#         generateModJsons(modName, "Forge", logs, common)
#
#
#
# for compat in modCompatFiles:
#     file = open(modCompatPath + "/" + compat)
#     if file.name.endswith(".json"):
#         print(file.name)
#         handleModCompat(json.load(file))
#
