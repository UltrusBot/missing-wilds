package me.ultrusmods.missingwilds.compat.template;

import io.github.cottonmc.templates.block.TemplateEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MissingWildsTemplateEntity extends TemplateEntity {
    public MissingWildsTemplateEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
