package me.ultrusmods.missingwilds;

import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MissingWildsForge {
    public MissingWildsForge() {
        MissingWildsModCommon.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::creativeTabRegister);
    }

    private void creativeTabRegister(CreativeModeTabEvent.Register event) {
        //TODO: Make this common code, since both use a builder through platform services
        event.registerCreativeModeTab(new ResourceLocation(Constants.MOD_ID, "items"), builder -> {
            builder.title(Component.translatable("itemGroup.missingwilds.items"));
            builder.icon(() -> new ItemStack(MissingWildsItems.FALLEN_BIRCH_LOG.get()));
            builder.displayItems((context, entries) -> {
                entries.accept(MissingWildsItems.FALLEN_OAK_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_BIRCH_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_SPRUCE_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_JUNGLE_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_DARK_OAK_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_ACACIA_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_MANGROVE_LOG.get());
                entries.accept(MissingWildsItems.FALLEN_CRIMSON_STEM.get());
                entries.accept(MissingWildsItems.FALLEN_WARPED_STEM.get());
                entries.accept(MissingWildsItems.FALLEN_MUSHROOM_STEM.get());
                entries.accept(MissingWildsItems.BLUE_FORGET_ME_NOT.get());
                entries.accept(MissingWildsItems.PURPLE_FORGET_ME_NOT.get());
                entries.accept(MissingWildsItems.PINK_FORGET_ME_NOT.get());
                entries.accept(MissingWildsItems.WHITE_FORGET_ME_NOT.get());
                entries.accept(MissingWildsItems.SWEETSPIRE.get());
                entries.accept(MissingWildsItems.BROWN_POLYPORE_MUSHROOM.get());
                entries.accept(MissingWildsItems.ROASTED_POLYPORE_MUSHROOM.get());
            });
        });

    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MissingWildsModCommon::postInit);
    }


}