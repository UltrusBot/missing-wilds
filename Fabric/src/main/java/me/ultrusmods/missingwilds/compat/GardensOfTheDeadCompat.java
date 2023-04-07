package me.ultrusmods.missingwilds.compat;

public class GardensOfTheDeadCompat extends ModCompat {
    @Override
    public void addLogs() {
        addSimpleLog("soulblight_stem");
    }

    @Override
    public String getMod() {
        return "gardens_of_the_dead";
    }
}
