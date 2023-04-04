package me.ultrusmods.missingwilds.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record LogData(String name, String blockId, String logTexture, String strippedLogTexture) {
    public static final Codec<LogData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(LogData::name),
            Codec.STRING.fieldOf("block_id").forGetter(LogData::blockId),
            Codec.STRING.fieldOf("log_texture").forGetter(LogData::logTexture),
            Codec.STRING.fieldOf("inner_log_texture").forGetter(LogData::strippedLogTexture)
    ).apply(instance, LogData::new));
}
