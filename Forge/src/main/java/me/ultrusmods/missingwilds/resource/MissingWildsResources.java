package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.data.LogData;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.resources.ResourceLocation;

import static net.devtech.arrp.json.loot.JLootTable.*;

public class MissingWildsResources {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(Constants.id("resources"));
	public static final JTag FALLEN_LOGS = JTag.tag();

	public static void addLog(LogData logData, String modid) {
		var fallenLogLocation = Constants.id("block/" + modid + "_" + logData.name());
		var fallenLogLocationMossy = Constants.id("block/" + modid + "_" + logData.name() + "_mossy");
		var fallenLogLocationSnowy = Constants.id("block/" + modid + "_" + logData.name() + "_snowy");
		ResourceLocation id = Constants.id(modid + "_" + logData.name());
		var model = new JBlockModel(fallenLogLocation);
		var modelMossy = new JBlockModel(fallenLogLocationMossy);
		var modelSnowy = new JBlockModel(fallenLogLocationSnowy);
		RESOURCE_PACK.addBlockState(
				JState.state(
						JState.variant()
								.put("axis=x,cover=none", model.clone().y(90))
								.put("axis=y,cover=none", model.clone().x(90))
								.put("axis=z,cover=none", model.clone())
								.put("axis=x,cover=moss", modelMossy.clone().y(90))
								.put("axis=y,cover=moss", model.clone().x(90))
								.put("axis=z,cover=moss", modelMossy.clone())
								.put("axis=x,cover=snow", modelSnowy.clone().y(90))
								.put("axis=y,cover=snow", model.clone().x(90))
								.put("axis=z,cover=snow", modelSnowy.clone())

				),
				id
		);
		RESOURCE_PACK.addModel(
				JModel.model("missingwilds:block/template/fallen_log_template")
						.textures(new JTextures().var("log", logData.logTexture()).var("log_inner", logData.strippedLogTexture())),
				fallenLogLocation
		);
		RESOURCE_PACK.addModel(
				JModel.model("missingwilds:block/template/fallen_log_template_mossy")
						.textures(new JTextures().var("log", logData.logTexture()).var("log_inner", logData.strippedLogTexture())),
				fallenLogLocationMossy
		);
		RESOURCE_PACK.addModel(
				JModel.model("missingwilds:block/template/fallen_log_template_snowy")
						.textures(new JTextures().var("log", logData.logTexture()).var("log_inner", logData.strippedLogTexture())),
				fallenLogLocationSnowy
		);
		RESOURCE_PACK.addModel(
				JModel.model(fallenLogLocation),
				Constants.id("item/" + modid + "_" + logData.name())
		);
		FALLEN_LOGS.add(id);
		RESOURCE_PACK.addRecipe(
				Constants.id(modid + "_" + logData.name()), JRecipe.shaped(
						JPattern.pattern("xxx", "x x", "xxx"),
						JKeys.keys()
								.key("x", JIngredient.ingredient().item(modid + ":" + logData.name().substring(7))),
						JResult.result( "missingwilds:" + modid + "_" + logData.name())

				).group("missingwilds:fallen_logs")
		);
		RESOURCE_PACK.addLootTable(Constants.id(modid + "_" + logData.name()),
				loot("minecraft:block")
				.pool(pool()
						.rolls(1)
						.entry(entry()
								.type("minecraft:item")
								.name(id.toString()))
						.condition(condition("minecraft:survives_explosion"))));
	}
}
