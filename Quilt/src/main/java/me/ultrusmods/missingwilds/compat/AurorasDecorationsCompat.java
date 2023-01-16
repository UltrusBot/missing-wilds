package me.ultrusmods.missingwilds.compat;

public class AurorasDecorationsCompat extends ModCompatQuilt {
    @Override
    public void addLogs() {
        addSimpleLog("azalea_log");
        getLogList().add(new LogData("fallen_flowering_azalea_log" , getMod() + ":" + "flowering_azalea_log", getMod() + ":block/" + "flowering_azalea_log", getMod() + ":block/stripped_" + "azalea_log"));
        addSimpleLog("jacaranda_log");
    }

    @Override
    public String getMod() {
        return "aurorasdeco";
    }
}
