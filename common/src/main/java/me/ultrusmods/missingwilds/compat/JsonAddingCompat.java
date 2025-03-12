package me.ultrusmods.missingwilds.compat;

public interface JsonAddingCompat {
    /**
     * Is ran after all other json compat mods have been loaded.
     * @param resourceAdder The resource adder
     */
    void addJson(JsonDefinedModCompatInstance.ResourceAdder resourceAdder);
}
