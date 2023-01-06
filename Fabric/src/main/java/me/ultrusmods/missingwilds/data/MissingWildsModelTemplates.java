package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import static me.ultrusmods.missingwilds.data.MissingWildsTextureMappings.LOG;
import static me.ultrusmods.missingwilds.data.MissingWildsTextureMappings.LOG_INNER;

public class MissingWildsModelTemplates {

    public static final ModelTemplate FALLEN_LOG = create("template/fallen_log_template", LOG, LOG_INNER);
    public static final ModelTemplate FALLEN_LOG_MOSSY = create("template/fallen_log_template_mossy", "_mossy", LOG, LOG_INNER);
    public static final ModelTemplate FALLEN_LOG_SNOWY = create("template/fallen_log_template_snowy", "_snowy", LOG, LOG_INNER);
    private static ModelTemplate create(String $$0, TextureSlot... $$1) {
        return new ModelTemplate(Optional.of(new ResourceLocation(Constants.MOD_ID, "block/" + $$0)), Optional.empty(), $$1);
    }
    private static ModelTemplate create(String $$0, String string, TextureSlot... $$1) {
        return new ModelTemplate(Optional.of(new ResourceLocation(Constants.MOD_ID, "block/" + $$0)), Optional.of(string), $$1);
    }
}
