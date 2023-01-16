package me.ultrusmods.missingwilds.compat;

public class CinderscapesCompat extends ModCompatQuilt {
    @Override
    public void addLogs() {
        addSimpleLog("scorched_stem");
        addSimpleLog("umbral_stem");
    }

    @Override
    public String getMod() {
        return "cinderscapes";
    }
}
