package me.ultrusmods.missingwilds.compat;

public class TerrestriaCompat extends ModCompat {
    @Override
    public void addLogs() {
        addSimpleLog("hemlock_log");
        addSimpleLog("japanese_maple_log");
        addSimpleLog("rainbow_eucalyptus_log");
        addSimpleLog("redwood_log");
        addSimpleLog("rubber_log");
    }

    @Override
    public String getMod() {
        return "terrestria";
    }
}
