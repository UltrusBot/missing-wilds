package me.ultrusmods.missingwilds.compat.template;

import io.github.cottonmc.templates.api.TemplateInteractionUtil;
import io.github.cottonmc.templates.block.TemplateEntity;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.ModCompat;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TemplateModCompat {
    public static final Block TEMPLATE_FALLEN_LOG = MissingWildsBlocks.register("template_fallen_log", () -> new TemplateFallenLog(TemplateInteractionUtil.configureSettings(FabricBlockSettings.copy(MissingWildsBlocks.FALLEN_BIRCH_LOG.get())))).get();
    public static final Item TEMPLATE_FALLEN_LOG_ITEM = MissingWildsItems.register("template_fallen_log", () -> new BlockItem(TEMPLATE_FALLEN_LOG, new FabricItemSettings())).get();
    public static final BlockEntityType<TemplateEntity> TEMPLATE_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Constants.id("fallen_log"),
            FabricBlockEntityTypeBuilder.create(TemplateModCompat::createTemplateBlockEntity,
                    TEMPLATE_FALLEN_LOG
            ).build(null)
    );

    private static TemplateEntity createTemplateBlockEntity(BlockPos pos, BlockState state) {
        return new TemplateEntity(TEMPLATE_BLOCK_ENTITY, pos, state);
    }
    public static void init() {
        ModCompat.FALLEN_LOG_ITEMS.add(TEMPLATE_FALLEN_LOG_ITEM);

    }
}