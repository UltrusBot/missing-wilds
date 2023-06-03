package me.ultrusmods.missingwilds.compat;

public class PromenadeCompat extends ModCompat {
	@Override
	public void addLogs() {
		addSimpleLog("sakura");
		addSimpleLog("palm");
		addSimpleLog("maple");
		addSimpleStem("dark_amaranth");
	}

	@Override
	public void addSimpleLog(String logName) {
		getLogList().add(new LogData("fallen_" + logName + "_log", getMod() + ":" + logName + "_log", getMod() + ":block/" + logName + "/log/side", getMod() + ":block/" + logName + "/stripped_log/side"));
	}
	public void addSimpleStem(String logName) {
		getLogList().add(new LogData("fallen_" + logName + "_stem", getMod() + ":" + logName + "_stem", getMod() + ":block/" + logName + "/stem/side", getMod() + ":block/" + logName + "/stripped_stem/side"));
	}

	@Override
	public String getMod() {
		return "promenade";
	}
}
