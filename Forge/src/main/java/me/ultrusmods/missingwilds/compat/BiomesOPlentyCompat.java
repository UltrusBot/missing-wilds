package me.ultrusmods.missingwilds.compat;

public class BiomesOPlentyCompat extends ModCompatForge {
    @Override
    public void addLogs() {
        addSimpleLog("cherry_log");
        addSimpleLog("dead_log");
        addSimpleLog("fir_log");
        addSimpleLog("hellbark_log");
        addSimpleLog("jacaranda_log");
        addSimpleLog("magic_log");
        addSimpleLog("mahogany_log");
        addSimpleLog("palm_log");
        addSimpleLog("redwood_log");
        addSimpleLog("umbran_log");
        addSimpleLog("willow_log");
    }

    @Override
    public String getMod() {
        return "biomesoplenty";
    }
}
