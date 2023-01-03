package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class MissingWildsSounds {
    public static RegistrationProvider<SoundEvent> SOUNDS = RegistrationProvider.get(Registry.SOUND_EVENT, Constants.MOD_ID);

    public static final RegistryObject<SoundEvent> JAR_OPEN = SOUNDS.register("open_jar", () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, "open_jar")));
    public static final RegistryObject<SoundEvent> JAR_CLOSE = SOUNDS.register("close_jar", () -> new SoundEvent(new ResourceLocation(Constants.MOD_ID, "close_jar")));
    public static void init() {
    }
    
}
