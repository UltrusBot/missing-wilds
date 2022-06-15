package me.ultrusmods.missingwilds.stat;

import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.mixin.CustomStatAccessor;
import me.ultrusmods.missingwilds.register.RegistrationProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;

public class MissingWildsStats {
    public static final RegistrationProvider<ResourceLocation> CUSTOM_STATS = RegistrationProvider.get(Registry.CUSTOM_STAT, Constants.MOD_ID);

    public static void init() {

    }
    public static final ResourceLocation LOG_CRAWL_ONE_CM = CustomStatAccessor.makeCustomStat(Constants.MOD_ID + ":log_crawl_one_cm", StatFormatter.DISTANCE);

}
