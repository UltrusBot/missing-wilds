package me.ultrusmods.missingwilds.compat;

/**
 * Mod Compat that registers content, with methods that are called at the appropriate time.
 */
public abstract class RegisteringModCompat extends ModCompatInstance {

    public RegisteringModCompat(String modid) {
        super(modid);
    }

    public abstract void registerBlocks();
    public abstract void registerItems();
    public abstract void registerBlockEntities();
}
