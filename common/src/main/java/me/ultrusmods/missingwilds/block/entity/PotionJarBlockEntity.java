package me.ultrusmods.missingwilds.block.entity;

import me.ultrusmods.missingwilds.block.PotionJarBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class PotionJarBlockEntity extends BlockEntity {
    Holder<Potion> potion;
    int amount;
    public PotionJarBlockEntity(BlockPos pos, BlockState blockState) {
        super(MissingWildsBlockEntities.POTION_JAR, pos, blockState);
        this.potion = Potions.AWKWARD;
        this.amount = blockState.getValue(PotionJarBlock.POTION_LEVEL);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        var potion =  BuiltInRegistries.POTION.getHolder(ResourceLocation.parse(tag.getString("Potion")));
        if (potion.isEmpty()) {
            this.potion = Potions.AWKWARD;
        } else {
            this.potion = potion.get();
        }
    }
    
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("Potion", Objects.requireNonNullElse(BuiltInRegistries.POTION.getKey(potion.value()), ResourceLocation.fromNamespaceAndPath("minecraft", "awkward")).toString());
    }
    
    public int getColor() {
        return PotionContents.getColor(potion);
    }

    public boolean tryInsertPotion(ItemStack stack, Player player) {
        if (this.amount >= 3) {
            return false;
        }
        PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
        if (potionContents != null) {
            var optionalPotion = potionContents.potion();
            if (optionalPotion.isPresent()) {
                var potion = optionalPotion.get();
                if (this.potion.value() == potion.value()) {
                    this.amount = Math.min(this.amount + 1, 3);
                    stack.consume(1, player);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean setPotion(ItemStack stack,  int amount) {
        PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
        if (potionContents != null) {
            var optionalPotion = potionContents.potion();
            if (optionalPotion.isPresent()) {
                this.potion = optionalPotion.get();
                this.amount = amount;
                return true;
            }
        }
        return false;
    }

    public Holder<Potion> getPotion() {
        return potion;
    }

    public int getAmount() {
        return Math.min(this.amount, 3);
    }
    
    public int removePotionLevel() {
        this.amount--;
        return this.amount;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }
}
