package me.ultrusmods.missingwilds.platform;

import me.ultrusmods.missingwilds.MissingWildsMod;
import me.ultrusmods.missingwilds.MissingWildsModCommon;
import me.ultrusmods.missingwilds.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public CreativeModeTab getCreativeTab() {
        return MissingWildsMod.MISSING_WILD_ITEMS;
    }
}
