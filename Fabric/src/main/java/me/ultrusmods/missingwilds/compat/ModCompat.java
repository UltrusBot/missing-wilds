package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ModCompat {
	private final List<LogData> logList = new ArrayList<>();
	public static HashMap<String, ModCompat> modCompats = new HashMap<>();
	public static List<Item> FALLEN_LOG_ITEMS = new ArrayList<>();

	public static void checkModCompat() {
		modCompats.forEach((modId, modCompat) -> {
			if (FabricLoader.getInstance().isModLoaded(modId)) {
				modCompat.addLogs();
				modCompat.logList.forEach(logData -> {
					MissingWildsResources.addLog(logData, modId);
				});
			}
		});
		MissingWildsResources.RESOURCE_PACK.addTag(
				Constants.id("blocks/fallen_logs"),
				MissingWildsResources.FALLEN_LOGS
		);
	}

	public abstract void addLogs();

	public List<LogData> getLogList() {
		return logList;
	}

	public void addSimpleLog(String logName) {
		getLogList().add(new LogData("fallen_" + logName, getMod() + ":" + logName, getMod() + ":block/" + logName, getMod() + ":block/stripped_" + logName));
	}
	public abstract String getMod();


	public record LogData(String name, String blockId, String logTexture, String strippedLogTexture) {

	}
	static {
//		modCompats.put("minecraft", new MinecraftBase());
		modCompats.put("traverse", new TraverseModCompat());
		modCompats.put("byg", new BygModCompat());
		modCompats.put("blockus", new BlockusCompat());
		modCompats.put("promenade", new PromenadeCompat());
		modCompats.put("charm", new CharmCompat());
		modCompats.put("biomemakeover", new BiomeMakeoverCompat());
		modCompats.put("ecologics", new EcologicsCompat());
		modCompats.put("terrestria", new TerrestriaCompat());
		modCompats.put("cinderscapes", new CinderscapesCompat());
		modCompats.put("goodending", new GoodEndingCompat());
		modCompats.put("cobblemon", new CobblemonCompat());
		modCompats.put("gardens_of_the_dead", new GardensOfTheDeadCompat());
	}
}
