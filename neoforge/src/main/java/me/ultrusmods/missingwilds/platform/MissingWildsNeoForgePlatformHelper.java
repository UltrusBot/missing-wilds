package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.entity.FireflySwarm;
import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.function.BiFunction;

public class MissingWildsNeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void setBlockRenderType(RenderType layer, Block... blocks) {
        for (Block block : blocks) {
            //TODO: Look into alternative for this with multiloader.
            ItemBlockRenderTypes.setRenderLayer(block, layer);
        }
    }

    @Override
    public void duringItemRegistering() {

    }

    @Override
    public void duringBlockRegistering() {

    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(BiFunction<BlockPos, BlockState, T> supplier, Block... blocks) {
        return BlockEntityType.Builder.of(supplier::apply, blocks).build(null);
    }

    @Override
    public EntityType<FireflySwarm> createFirefly() {
        return EntityType.Builder.of(FireflySwarm::new, MobCategory.AMBIENT).sized(2.0F, 2.0F).clientTrackingRange(16).build("firefly_swarm");
    }
}