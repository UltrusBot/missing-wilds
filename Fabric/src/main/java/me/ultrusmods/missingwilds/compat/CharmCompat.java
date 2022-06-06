package me.ultrusmods.missingwilds.compat;

public class CharmCompat extends ModCompat {
    @Override
    public void addLogs() {
        addSimpleLog("azalea_log");
    }

    @Override
    public String getMod() {
        return "charm";
    }
}
