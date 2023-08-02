package me.ultrusmods.missingwilds.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record JarData(String name, ResourceLocation blockId, ResourceLocation jarTexture, ResourceLocation blockTexture) {
    public static final Codec<JarData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            // Name of the glass used for the jar, e.g. "red_stained", or "tinted"
            Codec.STRING.fieldOf("name").forGetter(JarData::name),
            // ID of the base block used to craft the jar, e.g. "minecraft:red_stained_glass"
            ResourceLocation.CODEC.fieldOf("block_id").forGetter(JarData::blockId),
            // Texture of the jar e.g. "missingwidlds:block/red_stained_jar"
            ResourceLocation.CODEC.fieldOf("jar_texture").forGetter(JarData::jarTexture),
            // Texture of the block e.g. "minecraft:block/red_stained_glass"
            ResourceLocation.CODEC.fieldOf("block_texture").forGetter(JarData::jarTexture)
    ).apply(instance, JarData::new));
}
