package me.ultrusmods.missingwilds.compat;

public class BlockusCompat extends ModCompat {
	@Override
	public void addLogs() {
		addSimpleLog("white_oak_log");
	}

	@Override
	public String getMod() {
		return "blockus";
	}
}
