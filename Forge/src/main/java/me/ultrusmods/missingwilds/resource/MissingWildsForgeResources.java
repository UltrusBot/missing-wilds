package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.ForgeModCompatHandler;
import me.ultrusmods.missingwilds.data.LogData;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import pers.solid.brrp.v1.RRPEventHelper;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.model.ModelJsonBuilder;


public class MissingWildsForgeResources {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(Constants.id("resources"));
	public static final TagBuilder FALLEN_LOGS = TagBuilder.create();

	public static void registerPack() {
		RRPEventHelper.BEFORE_VANILLA.registerPack(MissingWildsForgeResources.RESOURCE_PACK);

	}

	public static void generatePack() {
		ForgeModCompatHandler.modCompats.values().forEach((modCompat) -> {
			modCompat.logs().forEach(logEither -> {
				LogData logData = logEither.left().isPresent() ? ForgeModCompatHandler.getSimpleLogName(logEither.left().get(), modCompat.modid()) : logEither.right().get();
				MissingWildsForgeResources.addLog(logData, modCompat.modid());
			});
		});
		Constants.LOG.info("Generated missing wilds compat for mods: " + ForgeModCompatHandler.modCompats.keySet().stream().filter(Services.PLATFORM::isModLoaded).toList());

		MissingWildsForgeResources.RESOURCE_PACK.addTag(
				Constants.id("blocks/fallen_logs"),
				MissingWildsForgeResources.FALLEN_LOGS
		);
	}
	public static void addLog(LogData logData, String modid) {
		var fallenLogLocation = Constants.id("block/" + modid + "_" + logData.name());
		var fallenLogLocationMossy = Constants.id("block/" + modid + "_" + logData.name() + "_mossy");
		var fallenLogLocationSnowy = Constants.id("block/" + modid + "_" + logData.name() + "_snowy");
		ResourceLocation id = Constants.id(modid + "_" + logData.name());
		// TODO: Look at this again, try to figure out a good way to do this that is less, jank.
//		Block block = ForgeModCompatHandler.getLogsBlocks().get(logData.blockId()).get();
//		Item item = ForgeModCompatHandler.getLogItems().get(logData.blockId()).get();
//		var multistateVariant = MultiVariantGenerator.multiVariant(block).with(
//				PropertyDispatch.properties(BlockStateProperties.AXIS, FallenLogBlock.COVER)
//						.select(Direction.Axis.X, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Y, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogLocation).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Z, FallenLogBlock.Cover.NONE, Variant.variant().with(VariantProperties.MODEL, fallenLogLocation))
//						.select(Direction.Axis.X, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationMossy).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Y, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationMossy).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Z, FallenLogBlock.Cover.MOSSY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationMossy))
//						.select(Direction.Axis.X, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationSnowy).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Y, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationSnowy).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
//						.select(Direction.Axis.Z, FallenLogBlock.Cover.SNOWY, Variant.variant().with(VariantProperties.MODEL, fallenLogLocationSnowy))
//		);
//		RESOURCE_PACK.addBlockState(
//				id,
//				multistateVariant
//		);
		var blockState = """
				{
				  "variants": {
				    "axis=x,cover=none": {
				      "model": "%1$s",
				      "y": 90
				    },
				    "axis=y,cover=none": {
				      "model": "%1$s",
				      "x": 90
				    },
				    "axis=z,cover=none": {
				      "model": "%1$s"
				    },
				    "axis=x,cover=moss": {
				      "model": "%2$s",
				      "y": 90
				    },
				    "axis=y,cover=moss": {
				      "model": "%1$s",
				      "x": 90
				    },
				    "axis=z,cover=moss": {
				      "model": "%2$s"
				    },
				    "axis=x,cover=snow": {
				      "model": "%3$s",
				      "y": 90
				    },
				    "axis=y,cover=snow": {
				      "model": "%1$s",
				      "x": 90
				    },
				    "axis=z,cover=snow": {
				      "model": "%3$s"
				    }
				  }
				}
				""".replace("%1$s", fallenLogLocation.toString()).replace("%2$s", fallenLogLocationMossy.toString()).replace("%3$s", fallenLogLocationSnowy.toString());
		RESOURCE_PACK.addBlockState(id, blockState.getBytes());
		RESOURCE_PACK.addModel(fallenLogLocation, ModelJsonBuilder.create("missingwilds:block/template/fallen_log_template")
				.addTexture("log", logData.logTexture())
				.addTexture("log_inner", logData.strippedLogTexture())
		);
		RESOURCE_PACK.addModel(fallenLogLocationMossy, ModelJsonBuilder.create("missingwilds:block/template/fallen_log_template_mossy").addTexture("log", logData.logTexture())
				.addTexture("log_inner", logData.strippedLogTexture())
		);
		RESOURCE_PACK.addModel(fallenLogLocationSnowy, ModelJsonBuilder.create("missingwilds:block/template/fallen_log_template_snowy").addTexture("log", logData.logTexture())
				.addTexture("log_inner", logData.strippedLogTexture())
		);
		RESOURCE_PACK.addModel(
				Constants.id("item/" + modid + "_" + logData.name()),
				ModelJsonBuilder.create(fallenLogLocation)
		);
		FALLEN_LOGS.add(TagEntry.element(id));
		var recipe = """
				{
				  "type": "minecraft:crafting_shaped",
				  "group": "missingwilds:fallen_logs",
				  "key": {
				    "L": {
				      "item": "%1$s"
				    }
				  },
				  "pattern": [
				    "LLL",
				    "L L",
				    "LLL"
				  ],
				  "result": {
				    "count": 8,
				    "item": "%2$s"
				  }
				}
				""".strip().replace("%1$s", logData.blockId()).replace("%2$s", id.toString());
		RESOURCE_PACK.addRecipe(
				Constants.id(modid + "_" + logData.name()),
				recipe.getBytes()
		);
		var lootTable = """
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
				          "name": "%1$s"
				        }
				      ],
				      "rolls": 1.0
				    }
				  ]
				}
				""".strip().replace("%1$s", id.toString());
		RESOURCE_PACK.addLootTable(Constants.id(modid + "_" + logData.name()), lootTable.getBytes());
//		RESOURCE_PACK.addLootTable(Constants.id(modid + "_" + logData.name()),
//				LootTable.lootTable()
//						.withPool(
//								new LootPool.Builder()
//										.setRolls(ConstantValue.exactly(1))
//										.add(LootItem.lootTableItem(block))
//										.name(id.toString())
//										.when(ExplosionCondition.survivesExplosion())
//						).build());

	}
}
