package me.ultrusmods.missingwilds.client;


import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class MissingWildsClientForge {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_BIRCH_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_ACACIA_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_OAK_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_SPRUCE_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_DARK_OAK_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_JUNGLE_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_MANGROVE_LOG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_CRIMSON_STEM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_WARPED_STEM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.FALLEN_MUSHROOM_STEM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.BROWN_POLYPORE_MUSHROOM.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.BLUE_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.PURPLE_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.PINK_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.WHITE_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.SWEETSPIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.POTTED_SWEETSPIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.POTTED_BLUE_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.POTTED_WHITE_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.POTTED_PINK_FORGET_ME_NOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MissingWildsBlocks.POTTED_PURPLE_FORGET_ME_NOT.get(), RenderType.cutout());
    }
}
