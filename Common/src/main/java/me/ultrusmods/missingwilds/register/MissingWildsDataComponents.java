package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.function.BiConsumer;

public class MissingWildsDataComponents {
    public static final DataComponentType<Integer> LIGHT_LEVEL = DataComponentType.<Integer>builder()
            .persistent(ExtraCodecs.intRange(1, 15))
            .networkSynchronized(ByteBufCodecs.VAR_INT)
            .build();
    public static void register(BiConsumer<DataComponentType, ResourceLocation> registerFunction) {
        registerFunction.accept(LIGHT_LEVEL, Constants.id("missingwilds:light_level"));
    }
}
