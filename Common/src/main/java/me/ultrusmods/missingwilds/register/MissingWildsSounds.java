package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.BiConsumer;

public class MissingWildsSounds {
    public static final SoundEvent JAR_OPEN = SoundEvent.createVariableRangeEvent(Constants.id("open_jar"));
    public static final SoundEvent JAR_CLOSE = SoundEvent.createVariableRangeEvent(Constants.id("close_jar"));
    public static void register(BiConsumer<ResourceLocation, SoundEvent> soundConsumer) {
        soundConsumer.accept(Constants.id("open_jar"), JAR_OPEN);
        soundConsumer.accept(Constants.id("close_jar"), JAR_CLOSE);
    }
    
}
