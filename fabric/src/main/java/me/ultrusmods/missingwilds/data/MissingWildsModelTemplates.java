package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

import static me.ultrusmods.missingwilds.data.MissingWildsTextureMappings.*;

public class MissingWildsModelTemplates {

    public static final ModelTemplate FALLEN_LOG = create("template/fallen_log_template", LOG, LOG_INNER);
    public static final ModelTemplate FALLEN_LOG_MOSSY = create("template/fallen_log_template_mossy", "_mossy", LOG, LOG_INNER);
    public static final ModelTemplate FALLEN_LOG_SNOWY = create("template/fallen_log_template_snowy", "_snowy", LOG, LOG_INNER);
    public static final ModelTemplate GLASS_JAR = create("template/glass_jar_template", JAR, GLASS);
    public static final ModelTemplate GLASS_JAR_OPEN = create("template/glass_jar_open_template", "_open", JAR, GLASS);
    public static final ModelTemplate FULL_POTION_JAR = create("template/potion_jar_template", JAR, GLASS);
    public static final ModelTemplate FULL_POTION_JAR_OPEN = create("template/potion_jar_open_template", "_open", JAR, GLASS);
    public static final ModelTemplate TWO_THIRDS_POTION_JAR = create("template/two_thirds_potion_jar_template", "_two_thirds", JAR, GLASS);
    public static final ModelTemplate TWO_THIRDS_POTION_JAR_OPEN = create("template/two_thirds_potion_jar_open_template", "_two_thirds_open", JAR, GLASS);
    public static final ModelTemplate ONE_THIRD_POTION_JAR = create("template/one_third_potion_jar_template", "_one_third", JAR, GLASS);
    public static final ModelTemplate ONE_THIRD_POTION_JAR_OPEN = create("template/one_third_potion_jar_open_template", "_one_third_open", JAR, GLASS);

    private static ModelTemplate create(String $$0, TextureSlot... $$1) {
        return new ModelTemplate(Optional.of(Constants.id("block/" + $$0)), Optional.empty(), $$1);
    }
    private static ModelTemplate create(String $$0, String string, TextureSlot... $$1) {
        return new ModelTemplate(Optional.of(Constants.id("block/" + $$0)), Optional.of(string), $$1);
    }
}
