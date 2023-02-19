package me.ultrusmods.missingwilds.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import me.ultrusmods.missingwilds.block.entity.FoodJarBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
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
        poseStack.mulPose(Quaternion.fromXYZDegrees(new Vector3f(90f, 0, 0)));
        poseStack.scale(0.45f, 0.45f, 0.45f);
        for (ItemStack itemStack : foodJarBlockEntity.getItems()) {
            poseStack.translate(0, 0, -0.0625);
            poseStack.mulPose(Quaternion.fromXYZDegrees(new Vector3f(0, 0, 22.5f)));
            this.itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, 0);
        }
        poseStack.popPose();
    }
}
