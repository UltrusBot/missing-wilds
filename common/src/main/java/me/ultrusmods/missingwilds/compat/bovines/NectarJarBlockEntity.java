package me.ultrusmods.missingwilds.compat.bovines;

import house.greenhouse.bovinesandbuttercups.content.component.BovinesDataComponents;
import house.greenhouse.bovinesandbuttercups.content.component.ItemNectar;
import house.greenhouse.bovinesandbuttercups.content.data.nectar.Nectar;
import house.greenhouse.bovinesandbuttercups.content.item.BovinesItems;
import me.ultrusmods.missingwilds.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NectarJarBlockEntity extends BlockEntity {
    ItemNectar nectar;
    int amount;
    public NectarJarBlockEntity(BlockPos pos, BlockState blockState) {
        super(BovinesAndButtercupsModCompat.NECTAR_JAR, pos, blockState);
        this.nectar = ItemNectar.EMPTY;
        this.amount = blockState.getValue(NectarJarBlock.NECTAR_LEVEL);
    }

    public boolean tryInsertNectar(ItemStack stack, Player player) {
        if (this.amount >= 3 || !stack.is(BovinesItems.NECTAR_BOWL)) {
            return false;
        }
        ItemNectar itemNectar = stack.get(BovinesDataComponents.NECTAR);
        if (itemNectar != null) {
            var nectarHolder = itemNectar.holder();
            if (this.nectar.holder().value() == nectarHolder.value()) {
                this.amount = Math.min(this.amount + 1, 3);
                stack.consume(1, player);
                player.addItem(new ItemStack(Items.BOWL));
                return true;
            }
        }
        return false;
    }
    public boolean setNectar(ItemStack stack, int amount) {
        ItemNectar itemNectar = stack.get(BovinesDataComponents.NECTAR);
        if (itemNectar != null) {
            this.nectar = itemNectar;
            this.amount = amount;
            return true;

        }
        return false;
    }
    
    public Holder<Nectar> getNectar() {
        return nectar.holder();
    }
    public ItemNectar getItemNectar() {
        return nectar;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Nectar")) {
            ItemNectar.CODEC.parse(registries.createSerializationContext(NbtOps.INSTANCE), tag.get("Nectar")).resultOrPartial((string) -> {
                Constants.LOG.error("Failed to parse jar nectar: '{}'", string);
            }).ifPresent((itemNectar) -> {
                this.nectar = itemNectar;
            });
        } else {
            this.nectar = ItemNectar.EMPTY;
        }
    }

    public int getAmount() {
        return Math.min(this.amount, 3);
    }

    public int removeNectarLevel() {
        this.amount--;
        return this.amount;
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.nectar.equals(ItemNectar.EMPTY)) {
            tag.put("Nectar", ItemNectar.CODEC.encodeStart(registries.createSerializationContext(NbtOps.INSTANCE), this.nectar).getOrThrow());
        }
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(BovinesDataComponents.NECTAR, nectar);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.nectar = componentInput.getOrDefault(BovinesDataComponents.NECTAR, ItemNectar.EMPTY);
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
