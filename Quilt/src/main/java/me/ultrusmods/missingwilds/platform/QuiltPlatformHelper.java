package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import java.util.function.BiFunction;

public class QuiltPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Quilt";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return QuiltLoader.isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return QuiltLoader.isDevelopmentEnvironment();
    }


    @Override
    public void setBlockRenderType(RenderType layer, Block... blocks) {
        BlockRenderLayerMap.put(layer, blocks);
    }

    @Override
    public void duringItemRegistering() {

    }

    @Override
    public void duringBlockRegistering() {

    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, Block... blocks) {
        return QuiltBlockEntityTypeBuilder.create(supplier::apply, blocks).build(null);
    }

    @Override
    public EntityType<FireflySwarm> createFirefly() {
        return QuiltEntityTypeBuilder.create(MobCategory.AMBIENT, FireflySwarm::new).setDimensions(EntityDimensions.scalable(2.0F, 2.0F)).maxBlockTrackingRange(16).build();
    }
}
