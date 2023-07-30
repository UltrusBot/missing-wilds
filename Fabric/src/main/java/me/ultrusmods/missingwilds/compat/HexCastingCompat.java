package me.ultrusmods.missingwilds.compat;

public class HexCastingCompat extends ModCompat {
    @Override
    public void addLogs() {
        addSimpleLog("edified_log");
        getLogList().add(new LogData(
                "fallen_edified_purple_log",
                "hexcasting:edified_log_purple",
                "hexcasting:block/deco/edified_log_purple",
                "hexcasting:block/stripped_edified_log"
        ));
        getLogList().add(new LogData(
                "fallen_edified_amethyst_log",
                "hexcasting:edified_log_amethyst",
                "hexcasting:block/deco/edified_log_amethyst",
                "hexcasting:block/stripped_edified_log"
        ));
        getLogList().add(new LogData(
                "fallen_edified_aventurine_log",
                "hexcasting:edified_log_aventurine",
                "hexcasting:block/deco/edified_log_aventurine",
                "hexcasting:block/stripped_edified_log"
        ));
        getLogList().add(new LogData(
                "fallen_edified_citrine_log",
                "hexcasting:edified_log_citrine",
                "hexcasting:block/deco/edified_log_citrine",
                "hexcasting:block/stripped_edified_log"
        ));
    }

    @Override
    public String getMod() {
        return "hexcasting";
    }
}
