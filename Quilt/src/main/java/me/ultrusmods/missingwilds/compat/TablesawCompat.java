package me.ultrusmods.missingwilds.compat;

import io.github.debuggyteam.tablesaw.api.TableSawAPI;
import io.github.debuggyteam.tablesaw.api.TableSawCompat;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.world.level.block.Blocks;

public class TablesawCompat implements TableSawCompat {
    @Override
    public void run(TableSawAPI api) {
        api.registerTableSawRecipe(Blocks.OAK_LOG.asItem(),1, MissingWildsItems.FALLEN_OAK_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.BIRCH_LOG.asItem(),1, MissingWildsItems.FALLEN_BIRCH_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.SPRUCE_LOG.asItem(),1, MissingWildsItems.FALLEN_SPRUCE_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.JUNGLE_LOG.asItem(),1, MissingWildsItems.FALLEN_JUNGLE_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.ACACIA_LOG.asItem(),1, MissingWildsItems.FALLEN_ACACIA_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.DARK_OAK_LOG.asItem(),1, MissingWildsItems.FALLEN_DARK_OAK_LOG.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.CRIMSON_STEM.asItem(),1, MissingWildsItems.FALLEN_CRIMSON_STEM.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.WARPED_STEM.asItem(),1, MissingWildsItems.FALLEN_WARPED_STEM.get().getDefaultInstance());
        api.registerTableSawRecipe(Blocks.MANGROVE_LOG.asItem(),1, MissingWildsItems.FALLEN_MANGROVE_LOG.get().getDefaultInstance());

    }
}
