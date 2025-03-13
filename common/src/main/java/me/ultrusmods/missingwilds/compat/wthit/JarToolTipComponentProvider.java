package me.ultrusmods.missingwilds.compat.wthit;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import me.ultrusmods.missingwilds.block.entity.ToolTipProvidingBlockEntity;

public class JarToolTipComponentProvider implements IBlockComponentProvider {

    static JarToolTipComponentProvider INSTANCE = new JarToolTipComponentProvider();

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlockEntity() instanceof ToolTipProvidingBlockEntity toolTipProvidingBlockEntity) {
            toolTipProvidingBlockEntity.getTooltip(tooltip::addLine);
        }
    }
}
