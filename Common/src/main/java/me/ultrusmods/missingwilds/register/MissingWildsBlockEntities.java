package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import me.ultrusmods.missingwilds.compat.RegisteringModCompat;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MissingWildsBlockEntities {
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<FireflyJarBlockEntity>> FIREFLY_JAR = BLOCK_ENTITY.register("firefly_jar", () -> Services.PLATFORM.buildBlockEntity(FireflyJarBlockEntity::new,
            JarMaps.JAR_TO_FIREFLY_JAR.values().toArray(Block[]::new)
            ));
    public static final RegistryObject<BlockEntityType<FoodJarBlockEntity>> FOOD_JAR = BLOCK_ENTITY.register("food_jar", () -> Services.PLATFORM.buildBlockEntity(FoodJarBlockEntity::new,
            JarMaps.JAR_TO_FOOD_JAR.values().toArray(Block[]::new)
            ));

    public static void init() {
        Services.PLATFORM.getModCompatHandler().getModCompats().forEach(modCompat -> {
            if (modCompat instanceof RegisteringModCompat) {
                ((RegisteringModCompat) modCompat).registerBlockEntities();
            }
        });
    }
}
