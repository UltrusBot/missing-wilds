package me.ultrusmods.missingwilds.compat.bovines;

import house.greenhouse.bovinesandbuttercups.api.block.BlockReference;
import house.greenhouse.bovinesandbuttercups.api.block.CustomFlowerType;
import house.greenhouse.bovinesandbuttercups.content.component.BovinesDataComponents;
import house.greenhouse.bovinesandbuttercups.content.data.configuration.MoobloomConfiguration;
import house.greenhouse.bovinesandbuttercups.content.data.nectar.Nectar;
import house.greenhouse.bovinesandbuttercups.registry.BovinesRegistryKeys;
import me.ultrusmods.missingwilds.compat.ModCompatClient;
import me.ultrusmods.missingwilds.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class BovinesAndButtercupsCompatClient implements ModCompatClient {

    Map<ResourceLocation, Integer> nectarToColor = new HashMap<>();
    
    @Override
    public void init() {
        BovinesAndButtercupsModCompat.JAR_TO_NECTAR_JAR.values().forEach(block -> Services.PLATFORM.setBlockRenderType(RenderType.translucent(), block));
    }
    
    public int getNectarColor(Holder<Nectar> nectar) {
        var optionalKey = nectar.unwrapKey();
        if (optionalKey.isPresent()) {
            if (nectarToColor.containsKey(optionalKey.get().location())) {
                return nectarToColor.get(optionalKey.get().location());
            }
            return addNectarColor(nectar);
        }
        return 0xFFFFFF;
    }

    public int addNectarColor(Holder<Nectar> nectar) {
        var optionalKey = nectar.unwrapKey();
        if (optionalKey.isPresent()) {
            var cowRegistry = Minecraft.getInstance().level.registryAccess().registryOrThrow(BovinesRegistryKeys.COW_VARIANT);
            var flowerCrownMaterials = Minecraft.getInstance().level.registryAccess().registryOrThrow(BovinesRegistryKeys.FLOWER_CROWN_MATERIAL);

            var cows = cowRegistry.holders().filter(cowVariantHolder -> {
                var cowVariant = cowVariantHolder.value();
                if (cowVariant.configuration() instanceof MoobloomConfiguration moobloomConfiguration) {
                    Optional<Holder<Nectar>> cowNectar = moobloomConfiguration.nectar();
                    BlockReference<Holder<CustomFlowerType>> flower = moobloomConfiguration.flower();
                    return cowNectar.isPresent() && cowNectar.get().equals(nectar);
                }
                return false;
            });
            var cow = cows.findFirst();
            if (cow.isEmpty()) return 0;
            
            var flowerCrown = flowerCrownMaterials.holders().filter(flowerCrownMaterialHolder -> {
                var flowerCrownMaterial = flowerCrownMaterialHolder.value();
                if (cow.get().value().configuration() instanceof MoobloomConfiguration moobloomConfiguration) {
                    var blockstate = moobloomConfiguration.flower().blockState();
                    return blockstate.isPresent() && flowerCrownMaterial.ingredient().is(blockstate.get().getBlock().asItem());
                }
                return false;
            }).findFirst();
            
            if (flowerCrown.isPresent()) {
                int color = flowerCrown.get().value().description().getStyle().getColor().getValue();
                nectarToColor.put(optionalKey.get().location(), color);
                return color;
            }
            nectarToColor.put(optionalKey.get().location(), 0xFFFFFF);
            
            
        }
        return 0xFFFFFF;
    }

    @Override
    public void onTagLoad() {
        nectarToColor.clear();
    }

    @Override
    public void registerBlockColors(BiConsumer<BlockColor, Block[]> registerBlockColors) {
        registerBlockColors.accept(
                (state, getter, pos, tintIndex) -> {
                    if (getter == null || pos == null) {
                        return 0xFFFFFF;
                    }
                    if (getter.getBlockEntity(pos) instanceof NectarJarBlockEntity potionJarBlockEntity) {
                        return getNectarColor(potionJarBlockEntity.getNectar());
                    }
                    return -1;
                    
                },
                BovinesAndButtercupsModCompat.JAR_TO_NECTAR_JAR.values().toArray(new Block[0])
        );
    }

    @Override
    public void registerItemColors(BiConsumer<ItemColor, ItemLike[]> registerItemColors) {
        registerItemColors.accept(
                (stack, tintIndex) -> {
                    if (stack.has(BovinesDataComponents.NECTAR) && tintIndex == 0) {
                        var itemNectar = stack.get(BovinesDataComponents.NECTAR);
                        return FastColor.ARGB32.opaque(getNectarColor(itemNectar.holder()));
                    }
                    return -1;
            
                },
                BovinesAndButtercupsModCompat.JAR_TO_NECTAR_JAR.values().toArray(new Block[0])
        );
    }
}
