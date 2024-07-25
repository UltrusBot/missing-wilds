package me.ultrusmods.missingwilds.stat;

import me.ultrusmods.missingwilds.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

import java.util.function.BiConsumer;

public class MissingWildsStats {
    public static final ResourceLocation LOG_CRAWL_ONE_CM = Constants.id("log_crawl_one_cm");
    public static void init(BiConsumer<ResourceLocation, ResourceLocation> consumer) {
        consumer.accept(LOG_CRAWL_ONE_CM, LOG_CRAWL_ONE_CM);
        Stats.CUSTOM.get(LOG_CRAWL_ONE_CM, StatFormatter.DISTANCE);
    }

}
