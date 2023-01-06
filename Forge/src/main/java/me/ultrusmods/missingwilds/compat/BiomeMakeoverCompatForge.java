package me.ultrusmods.missingwilds.compat;

public class BiomeMakeoverCompatForge extends ModCompatForge {
    @Override
    public void addLogs() {
        addSimpleLog("ancient_oak_log");
        addSimpleLog("blighted_balsa_log");
        addSimpleLog("swamp_cypress_log");
        addSimpleLog("willow_log");
    }

    @Override
    public String getMod() {
        return "biomemakeover";
    }
}
