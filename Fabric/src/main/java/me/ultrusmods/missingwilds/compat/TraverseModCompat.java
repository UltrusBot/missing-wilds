package me.ultrusmods.missingwilds.compat;

public class TraverseModCompat extends ModCompat {

	@Override
	public void addLogs() {
		addSimpleLog("fir_log");
	}

	@Override
	public String getMod() {
		return "traverse";
	}
}
