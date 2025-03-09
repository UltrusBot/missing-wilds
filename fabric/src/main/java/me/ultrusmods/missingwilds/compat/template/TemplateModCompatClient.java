package me.ultrusmods.missingwilds.compat.template;

import io.github.cottonmc.templates.api.TemplatesClientApi;
import io.github.cottonmc.templates.model.UnbakedJsonRetexturedModel;
import me.ultrusmods.missingwilds.Constants;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class TemplateModCompatClient {
    public static void init() {
        TemplatesClientApi.getInstance().json(Constants.id("block/template_fallen_log"));
        TemplatesClientApi.getInstance().json(Constants.id("block/template_fallen_log_snowy"));
        TemplatesClientApi.getInstance().json(Constants.id("block/template_fallen_log_mossy"));
        TemplatesClientApi.getInstance().addTemplateModel(Constants.id("special_fallen_template_log"), 
                new UnbakedJsonRetexturedModel(Constants.id("block/template_compat/template_fallen_log"))
        );
        TemplatesClientApi.getInstance().assignItemModel(Constants.id("special_fallen_template_log"), TemplateModCompat.TEMPLATE_FALLEN_LOG_ITEM);
        BlockRenderLayerMap.INSTANCE.putBlock(TemplateModCompat.TEMPLATE_FALLEN_LOG, RenderType.cutoutMipped());


    }
}
