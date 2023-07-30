package me.ultrusmods.missingwilds.compat.template;

import io.github.cottonmc.templates.api.TemplateInteractionUtil;
import io.github.cottonmc.templates.block.TemplateEntity;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.QuiltModCompatHandler;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class TemplateModCompat {
    public static final Block TEMPLATE_FALLEN_LOG = MissingWildsBlocks.register("template_fallen_log", () -> new TemplateFallenLog(TemplateInteractionUtil.configureSettings(QuiltBlockSettings.copy(MissingWildsBlocks.FALLEN_BIRCH_LOG.get())))).get();
    public static final Item TEMPLATE_FALLEN_LOG_ITEM = MissingWildsItems.register("template_fallen_log", () -> new BlockItem(TEMPLATE_FALLEN_LOG, new QuiltItemSettings())).get();
    public static final BlockEntityType<TemplateEntity> TEMPLATE_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            Constants.id("fallen_log"),
            QuiltBlockEntityTypeBuilder.create(TemplateModCompat::createTemplateBlockEntity,
                    TEMPLATE_FALLEN_LOG
            ).build(null)
    );

    private static TemplateEntity createTemplateBlockEntity(BlockPos pos, BlockState state) {
        return new TemplateEntity(TEMPLATE_BLOCK_ENTITY, pos, state);
    }
    public static void init() {
        QuiltModCompatHandler.FALLEN_LOG_ITEMS.add(TEMPLATE_FALLEN_LOG_ITEM);
    }
}