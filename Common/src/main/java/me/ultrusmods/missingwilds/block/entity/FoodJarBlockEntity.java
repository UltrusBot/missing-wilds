package me.ultrusmods.missingwilds.block.entity;

import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FoodJarBlockEntity extends BlockEntity {
    NonNullList<ItemStack> items;
    public FoodJarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MissingWildsBlockEntities.FOOD_JAR.get(), blockPos, blockState);
        this.items = NonNullList.withSize(16, ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        CompoundTag $$0 = new CompoundTag();
        ContainerHelper.saveAllItems($$0, this.items, true);
        return $$0;
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
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(16, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, this.items);
    }
}
