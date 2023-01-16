package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.MissingWildsQuilt;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.register.RegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.quiltmc.loader.api.QuiltLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ModCompatQuilt {
	private final List<LogData> logList = new ArrayList<>();
	public static HashMap<String, ModCompatQuilt> modCompats = new HashMap<>();
	public static void checkModCompat() {
		modCompats.forEach((modId, modCompat) -> {
			if (QuiltLoader.isModLoaded(modId)) {
				modCompat.addLogs();
				modCompat.logList.forEach(logData -> {
					RegistryObject<Block> block = MissingWildsBlocks.registerFallenLog(modId + "_" + logData.name());
					MissingWildsQuilt.COMPAT_LOGS.add(block.get());
					Item item = MissingWildsItems.register(modId + "_" + logData.name(), block).get();
				});
			}
		});
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
		modCompats.put("aurorasdeco", new AurorasDecorationsCompat());
	}
}
