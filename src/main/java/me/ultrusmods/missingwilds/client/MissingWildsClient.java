package me.ultrusmods.missingwilds.client;

import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MissingWildsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), MissingWildsBlocks.FALLEN_BIRCH_LOG, MissingWildsBlocks.FALLEN_ACACIA_LOG, MissingWildsBlocks.FALLEN_OAK_LOG, MissingWildsBlocks.FALLEN_SPRUCE_LOG, MissingWildsBlocks.FALLEN_DARK_OAK_LOG, MissingWildsBlocks.FALLEN_JUNGLE_LOG, MissingWildsBlocks.FALLEN_CRIMSON_STEM, MissingWildsBlocks.FALLEN_WARPED_STEM, MissingWildsBlocks.FALLEN_MUSHROOM_STEM, MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), MissingWildsBlocks.BLUE_FORGET_ME_NOT, MissingWildsBlocks.PURPLE_FORGET_ME_NOT, MissingWildsBlocks.PINK_FORGET_ME_NOT, MissingWildsBlocks.WHITE_FORGET_ME_NOT, MissingWildsBlocks.SWEETSPIRE);
		MissingWildsMod.compatLogs.forEach(block -> {
			BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
		});
	}
}
