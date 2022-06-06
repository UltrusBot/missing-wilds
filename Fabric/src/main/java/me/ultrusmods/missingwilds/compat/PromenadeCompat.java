package me.ultrusmods.missingwilds.compat;

public class PromenadeCompat extends ModCompat {
	@Override
	public void addLogs() {
		addSimpleLog("cherry_oak_log");
		addSimpleLog("palm_log");
		addSimpleLog("dark_amaranth_stem");
	}

	@Override
	public void addSimpleLog(String logName) {
		getLogList().add(new LogData("fallen_" + logName, getMod() + ":" + logName, getMod() + ":block/" + logName + "/side", getMod() + ":block/stripped_" + logName + "/side"));
	}

	@Override
	public String getMod() {
		return "promenade";
	}
}
