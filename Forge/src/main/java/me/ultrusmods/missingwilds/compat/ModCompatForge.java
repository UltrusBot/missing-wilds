package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.platform.Services;
import me.ultrusmods.missingwilds.register.RegistryObject;
import me.ultrusmods.missingwilds.resource.MissingWildsResources;
import net.devtech.arrp.api.RRPEventHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ModCompatForge {
	private final List<LogData> logList = new ArrayList<>();
	public static HashMap<String, ModCompatForge> modCompats = new HashMap<>();
	private final HashMap<String, RegistryObject<Item>> itemMap = new HashMap<>();
	private final HashMap<String, RegistryObject<Block>> blockMap = new HashMap<>();

	public ModCompatForge() {
	}

	public static void checkModCompat() {
		modCompats.forEach((modId, modCompat) -> {
			if (Services.PLATFORM.isModLoaded(modId)) {
				modCompat.logList.forEach(logData -> {
					MissingWildsResources.addLog(logData, modId);
				});
			}
		});
		MissingWildsResources.RESOURCE_PACK.addTag(
				Constants.id("blocks/fallen_logs"),
				MissingWildsResources.FALLEN_LOGS
		);
		RRPEventHelper.BEFORE_VANILLA.registerPack(MissingWildsResources.RESOURCE_PACK);
	}

	public static void registerModComatBlocks() {
		modCompats.forEach((modId, modCompat) -> {
			if (Services.PLATFORM.isModLoaded(modId)) {
				modCompat.addLogs();
				modCompat.logList.forEach(logData -> {
					MissingWildsResources.addLogBlock(logData, modId, modCompat);
				});
			}
		});
	}

	public static void registerModCompatItems() {
		modCompats.forEach((modId, modCompat) -> {
			if (Services.PLATFORM.isModLoaded(modId)) {
				modCompat.logList.forEach(logData -> {
					MissingWildsResources.addLogItem(logData, modId, modCompat);
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

	public HashMap<String, RegistryObject<Block>> getLogsBlocks() {
		return blockMap;
	}
	public HashMap<String, RegistryObject<Item>> getLogItems() {
		return itemMap;
	}
	public void addBlock(RegistryObject<Block> block, String blockId) {
		blockMap.put(blockId, block);
	};
	public void addItem(RegistryObject<Item> item, String itemId) {
		itemMap.put(itemId, item);
	};


	public record LogData(String name, String blockId, String logTexture, String strippedLogTexture) {

	}
	static {
//		modCompats.put("minecraft", new MinecraftBase());
		modCompats.put("byg", new BygModCompatForge());
		modCompats.put("biomemakeover", new BiomeMakeoverCompatForge());
		modCompats.put("ecologics", new EcologicsCompatForge());
		modCompats.put("goodending", new GoodEndingCompatForge());
	}
}
