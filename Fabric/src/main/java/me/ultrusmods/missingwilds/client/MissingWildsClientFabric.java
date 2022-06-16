package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.MissingWildsFabric;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class MissingWildsClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(), MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), MissingWildsBlocks.FALLEN_OAK_LOG.get(), MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(), MissingWildsBlocks.FALLEN_WARPED_STEM.get(), MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get(), MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM.get());
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), MissingWildsBlocks.BLUE_FORGET_ME_NOT.get(), MissingWildsBlocks.PURPLE_FORGET_ME_NOT.get(), MissingWildsBlocks.PINK_FORGET_ME_NOT.get(), MissingWildsBlocks.WHITE_FORGET_ME_NOT.get(), MissingWildsBlocks.SWEETSPIRE.get(), MissingWildsBlocks.POTTED_SWEETSPIRE.get(), MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT.get(), MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT.get(), MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT.get(), MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT.get());
		MissingWildsFabric.COMPAT_LOGS.forEach(block -> {
			BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutoutMipped());
		});
	}
}
