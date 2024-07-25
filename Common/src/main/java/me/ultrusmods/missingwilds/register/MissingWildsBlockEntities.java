package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.RegisteringModCompat;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.BiConsumer;

public class MissingWildsBlockEntities {
    public static final BlockEntityType<FireflyJarBlockEntity> FIREFLY_JAR = Services.PLATFORM.buildBlockEntity(FireflyJarBlockEntity::new, JarMaps.JAR_TO_FIREFLY_JAR.values().toArray(Block[]::new));
    public static final BlockEntityType<FoodJarBlockEntity> FOOD_JAR = Services.PLATFORM.buildBlockEntity(FoodJarBlockEntity::new, JarMaps.JAR_TO_FOOD_JAR.values().toArray(Block[]::new));

    public static void register(BiConsumer<ResourceLocation, BlockEntityType<?>> blockEntityConsumer) {
        blockEntityConsumer.accept(Constants.id("firefly_jar"), FIREFLY_JAR);
        blockEntityConsumer.accept(Constants.id("food_jar"), FOOD_JAR);
        ModCompatHandler.getModCompats().forEach(modCompat -> {
            if (modCompat instanceof RegisteringModCompat) {
                ((RegisteringModCompat) modCompat).registerBlockEntities();
            }
        });
    }
}
