package me.ultrusmods.missingwilds.block.entity;

import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class FoodJarBlockEntity extends BlockEntity implements ToolTipProvidingBlockEntity {
    NonNullList<ItemStack> items;
    public FoodJarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MissingWildsBlockEntities.FOOD_JAR, blockPos, blockState);
        this.items = NonNullList.withSize(16, ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        ContainerHelper.saveAllItems(tag, this.items, registries);
        return tag;
    }

    public boolean addItems(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (!items.get(0).isEmpty() && !stack.is(items.get(0).getItem())) {
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isEmpty()) {
                var item = stack.copy();
                item.setCount(1);
                items.set(i, item);
                stack.shrink(1);
                setChanged();
                return true;
            }
        }
        return false;
    }

    public ItemStack removeItem() {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (!items.get(i).isEmpty()) {
                ItemStack stack = items.get(i);
                items.set(i, ItemStack.EMPTY);
                setChanged();
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(16, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
    }
    
    public int getCount() {
        return this.items.stream().filter(items -> !items.isEmpty()).toList().size();
    }

    @Override
    public void getTooltip(Consumer<Component> adder) {
        if (!this.items.isEmpty()) {
            var item = this.items.getFirst().getHoverName().copy().append(Component.literal(" x" + getCount()));
            adder.accept(item);
        }
    }
}
