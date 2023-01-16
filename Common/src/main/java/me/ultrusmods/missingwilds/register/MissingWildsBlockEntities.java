package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MissingWildsBlockEntities {
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY = RegistrationProvider.get(Registry.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<FireflyJarBlockEntity>> FIREFLY_JAR = BLOCK_ENTITY.register("firefly_jar", () -> Services.PLATFORM.buildBlockEntity(FireflyJarBlockEntity::new,
            MissingWildsBlocks.FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.TINTED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.WHITE_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.ORANGE_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.MAGENTA_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.LIGHT_BLUE_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.YELLOW_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.LIME_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.PINK_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.GRAY_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.LIGHT_GRAY_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.CYAN_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.PURPLE_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.BLUE_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.BROWN_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.GREEN_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.RED_STAINED_FIREFLY_JAR_BLOCK.get(),
            MissingWildsBlocks.BLACK_STAINED_FIREFLY_JAR_BLOCK.get()
            ));

    public static void init() {

    }
}
