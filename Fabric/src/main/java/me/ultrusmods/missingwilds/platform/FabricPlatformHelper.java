package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.MissingWildsFabric;
import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }



    @Override
    public CreativeModeTab getCreativeTab() {
        return MissingWildsFabric.MISSING_WILD_ITEMS;
    }

    @Override
    public void setBlockRenderType(RenderType layer, Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(layer, blocks);
    }

    @Override
    public void duringItemRegistering() {

    }

    @Override
    public void duringBlockRegistering() {

    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(supplier::apply, blocks).build(null);
    }
}
