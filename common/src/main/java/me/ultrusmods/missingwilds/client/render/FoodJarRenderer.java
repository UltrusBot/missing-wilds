package me.ultrusmods.missingwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FoodJarRenderer implements BlockEntityRenderer<FoodJarBlockEntity> {
    private final ItemRenderer itemRenderer;

    public FoodJarRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }



    @Override
    public void render(FoodJarBlockEntity foodJarBlockEntity, float tickDelta, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0, 0.5f);
//        poseStack.mulPose(Quaternion.fromXYZDegrees(new Vector3f(90f, 0, 0)));
        poseStack.mulPose(Axis.XP.rotationDegrees(90f));
        poseStack.scale(0.45f, 0.45f, 0.45f);
        for (ItemStack itemStack : foodJarBlockEntity.getItems()) {
            poseStack.translate(0, 0, -0.0625);
            poseStack.mulPose(Axis.ZP.rotationDegrees(22.5F));
            this.itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, null, 0);
        }
        poseStack.popPose();
    }
}
