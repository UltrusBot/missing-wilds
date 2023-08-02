package me.ultrusmods.missingwilds.compat;

import me.ultrusmods.missingwilds.platform.Services;

public abstract class ModCompatInstance {
    public String modid;

    public ModCompatInstance(String modid) {
        this.modid = modid;
    }
    abstract public void init();

    abstract public void clientInit();

    /**
     * Checks if the mod compat is active
     * Default impl checks if the mod is loaded.
     * @return true if this mod compat is active
     */
    public boolean isActive() {
        return Services.PLATFORM.isModLoaded(modid);
    }

    public String getModid() {
        return modid;
    }

}
