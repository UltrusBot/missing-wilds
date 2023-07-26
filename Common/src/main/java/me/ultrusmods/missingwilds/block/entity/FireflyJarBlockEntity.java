package me.ultrusmods.missingwilds.block.entity;

import me.ultrusmods.missingwilds.ColorSets;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.particle.FireflyParticleOptions;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

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

    public void mixColor(float[] dyeColor) {
        int curRed = (this.color & 0xFF0000) >> 16;
        int curGreen = (this.color & 0xFF00) >> 8;
        int curBlue = (this.color & 0xFF);

        int newRed = (int)(dyeColor[0] * 255);
        int newGreen = (int)(dyeColor[1] * 255);
        int newBlue = (int)(dyeColor[2] * 255);

        int red = (curRed + newRed) / 2;
        int green = (curGreen + newGreen) / 2;
        int blue = (curBlue + newBlue) / 2;

        Constants.LOG.info("blendedRed: " + red + " blendedGreen: " + green + " blendedBlue: " + blue);

        this.color = (red << 16) | (green << 8) | blue;

        this.setChanged();
    }

    public void createParticles(Level level, int lightLevel, BlockPos pos, RandomSource random) {
        ColorSets.ColorSet colorSet = null;
        if (this.name != null) {
            colorSet = ColorSets.COLOR_SETS.getOrDefault(this.name.getString(), null);
        }
        for (int i = 0; i < lightLevel / 2; i++) {
            double x = pos.getX() + 0.5 + (2 * random.nextDouble() - 1);
            double y = pos.getY() + 0.5 + (random.nextInt(3) + 1);
            double z = pos.getZ() + 0.5 + (2 * random.nextDouble() - 1);
            spawnFireflies(level, random, colorSet, x, y, z, 0.0075f);
        }

    }

    public void createInnerParticles(Level level, int lightLevel, BlockPos pos, RandomSource random) {
        ColorSets.ColorSet colorSet = null;
        if (this.name != null) {
            colorSet = ColorSets.COLOR_SETS.get(this.name.getString());
        }
        for (int i = 0; i < 3; i++) {
            double x = (pos.getX() + 0.5) + ((2 * random.nextDouble() - 1) / 5.0f);
            double y = (pos.getY() + 0.5) + ((2 * random.nextDouble() - 1) / 4.0f);
            double z = (pos.getZ() + 0.5) + ((2 * random.nextDouble() - 1) / 5.0f);
            spawnFireflies(level, random, colorSet, x, y, z, 0f);
        }
    }

    private void spawnFireflies(Level level, RandomSource random, ColorSets.ColorSet colorSet, double x, double y, double z, float speed) {
        if (colorSet != null) {
            var color = colorSet.colors[random.nextInt(colorSet.colors.length)];
            level.addParticle(new FireflyParticleOptions(color[0]/255.0F, color[1]/255.0F, color[2]/255.0F, 0.5f, speed), x, y, z, 0, 0, 0);
        } else {
            var color = getColorArr();
            level.addParticle(new FireflyParticleOptions(color[0], color[1], color[2], 0.5f, speed), x, y, z, 0, 0, 0);
        }
    }

    public double[] getColorArr() {
        return new double[]{((this.color & 0xFF0000) >> 16) / 255.0D, ((this.color & 0xFF00) >> 8) / 255.0D, (this.color & 0xFF) / 255.0D};
    }

    public int getColor() {
        return color;
    }

    @Override
    public Component getCustomName() {
        return name;
    }
}
