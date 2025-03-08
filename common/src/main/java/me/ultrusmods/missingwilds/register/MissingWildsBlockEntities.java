package me.ultrusmods.missingwilds.register;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.JarMaps;
import me.ultrusmods.missingwilds.block.entity.FireflyJarBlockEntity;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import me.ultrusmods.missingwilds.block.entity.PotionJarBlockEntity;
import me.ultrusmods.missingwilds.compat.ModCompatHandler;
import me.ultrusmods.missingwilds.compat.RegisteringModCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MissingWildsBlockEntities {
    public static final BlockEntityType<FireflyJarBlockEntity> FIREFLY_JAR = buildBlockEntity(FireflyJarBlockEntity::new, JarMaps.JAR_TO_FIREFLY_JAR.values().toArray(Block[]::new));
    public static final BlockEntityType<FoodJarBlockEntity> FOOD_JAR = buildBlockEntity(FoodJarBlockEntity::new, JarMaps.JAR_TO_FOOD_JAR.values().toArray(Block[]::new));
    public static final BlockEntityType<PotionJarBlockEntity> POTION_JAR = buildBlockEntity(PotionJarBlockEntity::new, JarMaps.JAR_TO_POTION_JAR.values().toArray(Block[]::new));
    
    public static void register(BiConsumer<ResourceLocation, BlockEntityType<?>> blockEntityConsumer) {
        blockEntityConsumer.accept(Constants.id("firefly_jar"), FIREFLY_JAR);
        blockEntityConsumer.accept(Constants.id("food_jar"), FOOD_JAR);
        blockEntityConsumer.accept(Constants.id("potion_jar"), POTION_JAR);
        ModCompatHandler.getModCompats().forEach(modCompat -> {
            if (modCompat instanceof RegisteringModCompat) {
                ((RegisteringModCompat) modCompat).registerBlockEntities();
            }
        });
    }

    public static <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, Block... blocks) {
        return BlockEntityType.Builder.of(supplier::apply, blocks).build(null);
    }
}
