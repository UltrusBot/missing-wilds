package me.ultrusmods.missingwilds.data;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

// TODO: Add glss jars list.
public record ModCompat(String modid, List<Either<String, LogData>> logs) {
    public static final Codec<ModCompat> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("modid").forGetter(ModCompat::modid),
            Codec.either(Codec.STRING, LogData.CODEC).listOf().fieldOf("logs").forGetter(ModCompat::logs)
    ).apply(instance, ModCompat::new));
}
