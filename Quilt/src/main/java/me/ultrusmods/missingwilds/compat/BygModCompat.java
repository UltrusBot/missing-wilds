package me.ultrusmods.missingwilds.compat;

public class BygModCompat extends ModCompatQuilt {
	@Override
	public void addLogs() {
		addBygLog("aspen");
		addBygLog("baobab");
		addBygLog("blue_enchanted");
		addBygLog("cherry");
		addBygLog("cika");
		addBygLog("cypress");
		addBygLog("ebony");
		addBygLog("ether");
		addBygLog("fir");
		addBygLog("green_enchanted");
		addBygLog("holly");
		addBygLog("jacaranda");
		addBygLog("lament");
		addBygLog("mahogany");
		addBygLog("white_mangrove");
		addBygLog("maple");
		addBygLog("nightshade");
		addBygLog("palm");
		addBygLog("pine");
		addBygLog("rainbow_eucalyptus");
		addBygLog("redwood");
		addBygLog("skyris");
		addBygStem("bulbis");
//		addSimpleLog("imparius");    no stripped variant
		addBygStem("sythian");

		addSimpleLog("palo_verde_log");


	}


	public void addBygLog(String logName) {
		getLogList().add(new LogData("fallen_%s_log".formatted(logName), "%s:%s_log".formatted(getMod(), logName), "%s:block/%s/log".formatted(getMod(), logName), "%s:block/%s/stripped_log".formatted(getMod(), logName)));
	}

	public void addBygStem(String logName) {
		getLogList().add(new LogData("fallen_%s_stem".formatted(logName), "%s:%s_stem".formatted(getMod(), logName), "%s:block/%s/log".formatted(getMod(), logName), "%s:block/%s/stripped_log".formatted(getMod(), logName)));
	}

	@Override
	public String getMod() {
		return "byg";
	}
}
