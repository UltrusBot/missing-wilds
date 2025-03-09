package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potion;

public class MissingWildsDataComponents {
    public static final DataComponentType<Holder<Potion>> POTION = DataComponentType.<Holder<Potion>>builder()
            .persistent(Potion.CODEC)
            .networkSynchronized(Potion.STREAM_CODEC)
            .build();
    public static void register() {
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Constants.id("potion"), POTION);
    }
}
