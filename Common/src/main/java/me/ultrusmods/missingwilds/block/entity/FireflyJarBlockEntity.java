package me.ultrusmods.missingwilds.block.entity;

import me.ultrusmods.missingwilds.ColorSets;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class FireflyJarBlockEntity extends BlockEntity implements Nameable {
    private int color = 7601920;

    private Component name;

    public FireflyJarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MissingWildsBlockEntities.FIREFLY_JAR.get(), blockPos, blockState);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("color")) {
            this.color = tag.getInt("color");
        }
        if (tag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("color", this.color);
        if (this.name != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
    }



    @Override
    public Component getName() {
        return name;
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    public void setCustomName(Component name) {
        this.name = name;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void mixColor(int color) {
        int curRed = (this.color >> 16) & 0xFF;
        int curGreen = (this.color >> 8) & 0xFF;
        int curBlue = this.color & 0xFF;

        int newRed = (color >> 16) & 0xFF;
        int newGreen = (color >> 8) & 0xFF;
        int newBlue = color & 0xFF;

        int red = (curRed + newRed) / 2;
        int green = (curGreen + newGreen) / 2;
        int blue = (curBlue + newBlue) / 2;

        this.color = Mth.color(red, green, blue);

        this.setChanged();
    }

    public void createParticles(Level level, int lightLevel, BlockPos pos, RandomSource random) {
        Color[] colorSet = null;
        if (this.name != null) {
            colorSet = ColorSets.COLOR_SETS.get(this.name.getString());
        }
        for (int i = 0; i < lightLevel / 2; i++) {
            double x = pos.getX() + 0.5 + (2 * random.nextDouble() - 1);
            double y = pos.getY() + 0.5 + (random.nextInt(3) + 1);
            double z = pos.getZ() + 0.5 + (2 * random.nextDouble() - 1);
            // TODO: Revisit this
            if (colorSet != null) {
                var color = colorSet[random.nextInt(colorSet.length)].getRGBColorComponents(null);
                level.addParticle(new FireflyParticleOptions(color[0], color[1], color[2]), x, y, z, color[0], color[1], color[2]);
            } else {
                var color = getColorArr();
                level.addParticle(new FireflyParticleOptions(color[0], color[1], color[2]), x, y, z, color[0], color[1], color[2]);
            }
        }
    }

    public double[] getColorArr() {
        return new double[]{((this.color >> 16) & 0xFF) / 255.0, ((this.color >> 8) & 0xFF) / 255.0, (this.color & 0xFF) / 255.0};
    }

    public int getColor() {
        return color;
    }
}