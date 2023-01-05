package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MissingWildsBlockEntities {
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY = RegistrationProvider.get(Registry.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    public static final RegistryObject<BlockEntityType<FireflyJarBlockEntity>> FIREFLY_JAR = BLOCK_ENTITY.register("firefly_jar", () -> Services.PLATFORM.buildBlockEntity(FireflyJarBlockEntity::new, MissingWildsBlocks.FIREFLY_JAR_BLOCK.get()));

    public static void init() {

    }
}
