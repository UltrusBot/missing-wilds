package me.ultrusmods.missingwilds.compat.wthit;

import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;
import me.ultrusmods.missingwilds.block.entity.ToolTipProvidingBlockEntity;

public class MissingWildsWailaPlugin implements IWailaClientPlugin {

    @Override
    public void register(IClientRegistrar iClientRegistrar) {
        iClientRegistrar.body(
                JarToolTipComponentProvider.INSTANCE,
                ToolTipProvidingBlockEntity.class
        );
    }
}
