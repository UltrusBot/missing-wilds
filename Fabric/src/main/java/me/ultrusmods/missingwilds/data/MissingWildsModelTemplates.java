package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class MissingWildsModelTemplates {

    public static final ModelTemplate FALLEN_LOG = create("template/fallen_log_template", TextureSlot.create("log"), TextureSlot.create("log_inner"));
    public static final ModelTemplate FALLEN_LOG_MOSSY = create("template/fallen_log_template_mossy", TextureSlot.create("log"), TextureSlot.create("log_inner"));
    private static ModelTemplate create(String $$0, TextureSlot... $$1) {
        return new ModelTemplate(Optional.of(new ResourceLocation(Constants.MOD_ID, "block/" + $$0)), Optional.empty(), $$1);
    }
}
