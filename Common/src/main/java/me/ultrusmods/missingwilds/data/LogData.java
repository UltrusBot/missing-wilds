package me.ultrusmods.missingwilds.data;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public record LogData(String name, ResourceLocation blockId, ResourceLocation logTexture, ResourceLocation strippedLogTexture) {
    public static final Codec<LogData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            // This is the name of the log, e.g. "fallen_oak_log", will be used for the id of the item & block.
            Codec.STRING.fieldOf("name").forGetter(LogData::name),
            // This is the id of the base log e.g. "minecraft:oak_log"
            ResourceLocation.CODEC.fieldOf("block_id").forGetter(LogData::blockId),
            // This is the texture of the log, e.g. "minecraft:block/oak_log"
            ResourceLocation.CODEC.fieldOf("log_texture").forGetter(LogData::logTexture),

            ResourceLocation.CODEC.fieldOf("inner_log_texture").forGetter(LogData::strippedLogTexture)
    ).apply(instance, LogData::new));

    /**
     * This creates the log data from either a log data object, or a resource location of an log item that follows vanilla naming conventions.
     */
    public static Codec<LogData> LOG_DATA_OR_ITEM = Codec.either(LogData.CODEC, ResourceLocation.CODEC).xmap(either ->
            either.map(Function.identity(), LogData::getSimpleLogName), Either::left);

    public static LogData getSimpleLogName(ResourceLocation logId) {
        String logName = logId.getPath();
        String modId = logId.getNamespace();
        return new LogData("fallen_" + logName, new ResourceLocation(modId,  logName), new ResourceLocation(modId, "block/" + logName), new ResourceLocation(modId, "block/stripped_" + logName));
    }
}
