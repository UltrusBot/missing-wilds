package me.ultrusmods.missingwilds.compat.template;

import io.github.cottonmc.templates.TemplatesModelProvider;
import io.github.cottonmc.templates.model.UnbakedJsonRetexturedModel;
import me.ultrusmods.missingwilds.Constants;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class TemplateModCompatClient {
    public static TemplatesModelProvider provider = new TemplatesModelProvider();
    public static void init() {

            ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public ResourceLocation getFabricId() {
                return Constants.id("dump-caches");
            }

            @Override
            public void onResourceManagerReload(ResourceManager resourceManager) {
                provider.dumpCache();
            }
        });
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> provider);
        ModelLoadingRegistry.INSTANCE.registerVariantProvider(rm -> provider);
        provider.addTemplateModel(Constants.id("special_fallen_template_log"), new UnbakedJsonRetexturedModel(Constants.id("block/template_compat/template_fallen_log")));
        provider.addTemplateModel(Constants.id("special_fallen_template_log_mossy"), new UnbakedJsonRetexturedModel(Constants.id("block/template_compat/template_fallen_log_mossy")));
        provider.addTemplateModel(Constants.id("special_fallen_template_log_snowy"), new UnbakedJsonRetexturedModel(Constants.id("block/template_compat/template_fallen_log_snowy")));
        provider.assignItemModel(Constants.id("special_fallen_template_log"), TemplateModCompat.TEMPLATE_FALLEN_LOG_ITEM.get());
        BlockRenderLayerMap.put(RenderType.cutoutMipped(), TemplateModCompat.TEMPLATE_FALLEN_LOG.get());

    }
}
