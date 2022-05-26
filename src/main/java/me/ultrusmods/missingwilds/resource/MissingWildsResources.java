package me.ultrusmods.missingwilds.resource;

import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import static net.devtech.arrp.json.loot.JLootTable.*;

// TODO: Rewrite when have gotten more sleep
public class MissingWildsResources {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MissingWildsMod.id("resources"));
	public static final JTag FALLEN_LOGS = JTag.tag();
	public static void init() {
		RRPCallback.AFTER_VANILLA.register(a -> a.add(RESOURCE_PACK));

	}

	public static void addLog(ModCompat.LogData logData, String modid) {
		var fallenLogLocation = MissingWildsMod.id("block/" + modid + "_" + logData.name());
		var fallenLogLocationMossy = MissingWildsMod.id("block/" + modid + "_" + logData.name() + "_mossy");
		Block block = MissingWildsBlocks.registerFallenLog(modid + "_" + logData.name());
		MissingWildsMod.compatLogs.add(block);
		Item item = MissingWildsItems.register(modid + "_" + logData.name(), block);
		Identifier id = MissingWildsMod.id(modid + "_" + logData.name());
		var model = new JBlockModel(fallenLogLocation);
		var modelMossy = new JBlockModel(fallenLogLocationMossy);
		RESOURCE_PACK.addBlockState(
				JState.state(
						JState.variant()
								.put("facing=east,mossy=false", model.clone().y(270))
								.put("facing=east,mossy=true", modelMossy.clone().y(270))
								.put("facing=north,mossy=false", model.clone().y(180))
								.put("facing=north,mossy=true", modelMossy.clone().y(180))
								.put("facing=south,mossy=false", model.clone())
								.put("facing=south,mossy=true", modelMossy)
								.put("facing=west,mossy=false", model.clone().y(90))
								.put("facing=west,mossy=true", modelMossy.clone().y(90))
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
				JModel.model(fallenLogLocation),
				MissingWildsMod.id("item/" + modid + "_" + logData.name())
		);
		FALLEN_LOGS.add(id);
		RESOURCE_PACK.addRecipe(
				MissingWildsMod.id(logData.name()), JRecipe.shaped(
						JPattern.pattern("xxx", "x x", "xxx"),
						JKeys.keys()
								.key("x", JIngredient.ingredient().item(modid + ":" + logData.name().substring(7))),
						JResult.itemStack(item, 8)

				).group("missingwilds:fallen_logs")
		);
		RESOURCE_PACK.addLootTable(MissingWildsMod.id(modid + "_" + logData.name()),
				loot("minecraft:block")
				.pool(pool()
						.rolls(1)
						.entry(entry()
								.type("minecraft:item")
								.name(id.toString()))
						.condition(condition("minecraft:survives_explosion"))));
	}
}
