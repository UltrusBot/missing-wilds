package me.ultrusmods.missingwilds.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class CustomWaterlilyBlock extends WaterlilyBlock implements IPlantable {
    public CustomWaterlilyBlock(Properties p_58162_) {
        super(p_58162_);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.WATER;
    }
}
