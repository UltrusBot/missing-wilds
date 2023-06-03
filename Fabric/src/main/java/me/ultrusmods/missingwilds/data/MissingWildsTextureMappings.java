package me.ultrusmods.missingwilds.data;

import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;

import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class MissingWildsTextureMappings {
    public static final TextureSlot LOG = TextureSlot.create("log", TextureSlot.SIDE);
    public static final TextureSlot LOG_INNER = TextureSlot.create("log_inner", TextureSlot.SIDE);
    public static final TextureSlot JAR = TextureSlot.create("jar");
    public static final TextureSlot GLASS = TextureSlot.create("glass", TextureSlot.ALL);

    public static TextureMapping createFallenLog(Block log, Block innerLog) {
        return new TextureMapping().put(LOG, getBlockTexture(log)).put(LOG_INNER, getBlockTexture(innerLog));
    }
    public static TextureMapping createJar(Block jar, Block glass) {
        return new TextureMapping().put(JAR, getBlockTexture(jar)).put(GLASS, getBlockTexture(glass));
    }
}
