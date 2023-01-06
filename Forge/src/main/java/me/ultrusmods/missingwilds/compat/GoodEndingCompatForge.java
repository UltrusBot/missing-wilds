package me.ultrusmods.missingwilds.compat;

public class GoodEndingCompatForge extends ModCompatForge {
    @Override
    public void addLogs() {
        addSimpleLog("cypress_log");
        getLogList().add(new LogData("fallen_muddy_oak_log" , getMod() + ":" + "muddy_oak_log", getMod() + ":block/" + "muddy_oak_log", "minecraft:block/stripped_oak_log"));
    }

    @Override
    public String getMod() {
        return "goodending";
    }
}
