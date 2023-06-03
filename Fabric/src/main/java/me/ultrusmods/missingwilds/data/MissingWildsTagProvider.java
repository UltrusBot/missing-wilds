package me.ultrusmods.missingwilds.data;

import me.ultrusmods.missingwilds.register.MissingWildsItems;
import me.ultrusmods.missingwilds.tags.MissingWildsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class MissingWildsTagProvider extends FabricTagProvider<Item> {
    public MissingWildsTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(MissingWildsTags.FIREFLY_JARS)
                .add(MissingWildsItems.FIREFLY_JAR.get())
                .add(MissingWildsItems.TINTED_FIREFLY_JAR.get())
                .add(MissingWildsItems.WHITE_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.ORANGE_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.MAGENTA_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.LIGHT_BLUE_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.YELLOW_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.LIME_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.PINK_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.GRAY_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.LIGHT_GRAY_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.CYAN_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.PURPLE_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.BLUE_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.BROWN_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.GREEN_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.RED_STAINED_FIREFLY_JAR_ITEM.get())
                .add(MissingWildsItems.BLACK_STAINED_FIREFLY_JAR_ITEM.get());

    }
}
