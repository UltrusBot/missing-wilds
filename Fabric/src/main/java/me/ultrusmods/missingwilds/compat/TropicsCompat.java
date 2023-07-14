package me.ultrusmods.missingwilds.compat;

public class TropicsCompat extends ModCompat {
    @Override
    public void addLogs() {
        addSimpleLog("palm_log");
    }

    @Override
    public String getMod() {
        return "tropics";
    }
}
