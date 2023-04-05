package me.ultrusmods.missingwilds.compat;

public class CobblemonCompat extends ModCompat {
    @Override
    public void addLogs() {
        getLogList().add(new LogData(
                "fallen_apricorn_log",
                "cobblemon:apricorn_log",
                "cobblemon:blocks/apricorn_log",
                "cobblemon:blocks/stripped_apricorn_log"
        ));
    }

    @Override
    public String getMod() {
        return "cobblemon";
    }
}
