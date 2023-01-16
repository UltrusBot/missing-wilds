package me.ultrusmods.missingwilds.compat;

public class CharmCompat extends ModCompatQuilt {
    @Override
    public void addLogs() {
        addSimpleLog("azalea_log");
    }

    @Override
    public String getMod() {
        return "charm";
    }
}
