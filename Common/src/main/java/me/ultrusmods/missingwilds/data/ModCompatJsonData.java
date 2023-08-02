package me.ultrusmods.missingwilds.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record ModCompatJsonData(String modid, List<LogData> logs, List<JarData> jars) {
    public static final Codec<ModCompatJsonData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("modid").forGetter(ModCompatJsonData::modid),
            LogData.LOG_DATA_OR_ITEM.listOf().fieldOf("logs").forGetter(ModCompatJsonData::logs),
            JarData.CODEC.listOf().optionalFieldOf("jars", List.of()).forGetter(ModCompatJsonData::jars)
    ).apply(instance, ModCompatJsonData::new));

}
