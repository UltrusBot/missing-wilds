package me.ultrusmods.missingwilds.compat.bovines;

import com.google.common.collect.HashBiMap;
import house.greenhouse.bovinesandbuttercups.content.item.BovinesItems;
import me.ultrusmods.missingwilds.Constants;
import me.ultrusmods.missingwilds.compat.*;
import me.ultrusmods.missingwilds.register.MissingWildsBlockEntities;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import me.ultrusmods.missingwilds.register.MissingWildsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static me.ultrusmods.missingwilds.block.JarBlock.COVERED;
import static me.ultrusmods.missingwilds.compat.JsonDefinedModCompatInstance.getParentedModelText;

public class BovinesAndButtercupsModCompat extends RegisteringModCompat implements JarAddingCompat, JsonAddingCompat {
    public static HashBiMap<Block, Block> JAR_TO_NECTAR_JAR = HashBiMap.create();
    public static BlockEntityType<NectarJarBlockEntity> NECTAR_JAR;

    public BovinesAndButtercupsModCompat() {
        super("bovinesandbuttercups");
    }

    @Override
    public boolean isActive() {
        return super.isActive() && ModCompatHandler.isJsonModCompatEnabled();
    }

    @Override
    public void registerBlocks() {
        var jarBlock = getJarBlock();
        MissingWildsBlocks.register("nectar_jar", () -> jarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.JAR_BLOCK, jarBlock);
        var tintedJarBlock = getJarBlock();
        MissingWildsBlocks.register("tinted_nectar_jar", () -> tintedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.TINTED_JAR_BLOCK, tintedJarBlock);
        var whiteStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("white_stained_nectar_jar", () -> whiteStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK, whiteStainedJarBlock);
        var orangeStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("orange_stained_nectar_jar", () -> orangeStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK, orangeStainedJarBlock);
        var magentaStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("magenta_stained_nectar_jar", () -> magentaStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK, magentaStainedJarBlock);
        var lightBlueStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("light_blue_stained_nectar_jar", () -> lightBlueStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK, lightBlueStainedJarBlock);
        var yellowStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("yellow_stained_nectar_jar", () -> yellowStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK, yellowStainedJarBlock);
        var limeStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("lime_stained_nectar_jar", () -> limeStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK, limeStainedJarBlock);
        var pinkStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("pink_stained_nectar_jar", () -> pinkStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK, pinkStainedJarBlock);
        var grayStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("gray_stained_nectar_jar", () -> grayStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK, grayStainedJarBlock);
        var lightGrayStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("light_gray_stained_nectar_jar", () -> lightGrayStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK, lightGrayStainedJarBlock);
        var cyanStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("cyan_stained_nectar_jar", () -> cyanStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK, cyanStainedJarBlock);
        var purpleStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("purple_stained_nectar_jar", () -> purpleStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK, purpleStainedJarBlock);
        var blueStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("blue_stained_nectar_jar", () -> blueStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK, blueStainedJarBlock);
        var brownStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("brown_stained_nectar_jar", () -> brownStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK, brownStainedJarBlock);
        var greenStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("green_stained_nectar_jar", () -> greenStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK, greenStainedJarBlock);
        var redStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("red_stained_nectar_jar", () -> redStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.RED_STAINED_JAR_BLOCK, redStainedJarBlock);
        var blackStainedJarBlock = getJarBlock();
        MissingWildsBlocks.register("black_stained_nectar_jar", () -> blackStainedJarBlock);
        JAR_TO_NECTAR_JAR.put(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK, blackStainedJarBlock);
    }

    @Override
    public void registerItems() {
        MissingWildsItems.register("nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("tinted_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.TINTED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("white_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.WHITE_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("orange_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.ORANGE_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("magenta_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.MAGENTA_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("light_blue_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.LIGHT_BLUE_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("yellow_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.YELLOW_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("lime_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.LIME_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("pink_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.PINK_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("gray_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.GRAY_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("light_gray_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.LIGHT_GRAY_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("cyan_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.CYAN_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("purple_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.PURPLE_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("blue_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.BLUE_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("brown_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.BROWN_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("green_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.GREEN_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("red_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.RED_STAINED_JAR_BLOCK), new Item.Properties()));
        MissingWildsItems.register("black_stained_nectar_jar", () -> new NectarJarItem(JAR_TO_NECTAR_JAR.get(MissingWildsBlocks.BLACK_STAINED_JAR_BLOCK), new Item.Properties()));
    }

    @Override
    public void registerBlockEntities() {
        NECTAR_JAR = MissingWildsBlockEntities.buildBlockEntity(
                NectarJarBlockEntity::new,
                JAR_TO_NECTAR_JAR.values().toArray(Block[]::new)
        );
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.id("nectar_jar"), NECTAR_JAR);
    }

    @Override
    public void init() {

    }

    @Override
    public void clientInit() {

    }

    @Override
    public Block getJarBlock() {
        return new NectarJarBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.GLASS).noOcclusion());
    }

    @Override
    public Item getJarItem(Block baseJar) {
        return new NectarJarItem(JAR_TO_NECTAR_JAR.get(baseJar), new Item.Properties());
    }
    

    @Override
    public String getJarSuffix() {
        return "_nectar_jar";
    }

    @Override
    public void addToJarMap(Block baseJar, Block customJar) {
        JAR_TO_NECTAR_JAR.put(baseJar, customJar);
    }

    @Override
    public boolean validJarInteraction(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(BovinesItems.NECTAR_BOWL) && JAR_TO_NECTAR_JAR.get(state.getBlock()) instanceof NectarJarBlock jar) {
            level.setBlockAndUpdate(pos, jar.defaultBlockState().setValue(COVERED, state.getValue(COVERED)));
            NectarJarBlock.setNectar(level, pos, stack);
            stack.consume(1, player);
            player.addItem(new ItemStack(Items.BOWL));
            return true;
        }
        return false;
    }

    @Override
    public void addJson(JsonDefinedModCompatInstance.ResourceAdder resourceAdder) {
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/nectar_jar.json"), getNectarJarBlockstateJson("nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/tinted_nectar_jar.json"), getNectarJarBlockstateJson("tinted_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/white_stained_nectar_jar.json"), getNectarJarBlockstateJson("white_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/orange_stained_nectar_jar.json"), getNectarJarBlockstateJson("orange_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/magenta_stained_nectar_jar.json"), getNectarJarBlockstateJson("magenta_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/light_blue_stained_nectar_jar.json"), getNectarJarBlockstateJson("light_blue_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/yellow_stained_nectar_jar.json"), getNectarJarBlockstateJson("yellow_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/lime_stained_nectar_jar.json"), getNectarJarBlockstateJson("lime_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/pink_stained_nectar_jar.json"), getNectarJarBlockstateJson("pink_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/gray_stained_nectar_jar.json"), getNectarJarBlockstateJson("gray_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/light_gray_stained_nectar_jar.json"), getNectarJarBlockstateJson("light_gray_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/cyan_stained_nectar_jar.json"), getNectarJarBlockstateJson("cyan_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/purple_stained_nectar_jar.json"), getNectarJarBlockstateJson("purple_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/blue_stained_nectar_jar.json"), getNectarJarBlockstateJson("blue_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/brown_stained_nectar_jar.json"), getNectarJarBlockstateJson("brown_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/green_stained_nectar_jar.json"), getNectarJarBlockstateJson("green_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/red_stained_nectar_jar.json"), getNectarJarBlockstateJson("red_stained_nectar_jar"));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/black_stained_nectar_jar.json"), getNectarJarBlockstateJson("black_stained_nectar_jar"));
        addNectarJarModels(resourceAdder, "nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/glass"), Constants.id("block/jar"));
        addNectarJarModels(resourceAdder, "tinted_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/tinted_glass"), Constants.id("block/tinted_jar"));
        addNectarJarModels(resourceAdder, "white_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/white_stained_glass"), Constants.id("block/white_stained_jar"));
        addNectarJarModels(resourceAdder, "orange_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/orange_stained_glass"), Constants.id("block/orange_stained_jar"));
        addNectarJarModels(resourceAdder, "magenta_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/magenta_stained_glass"), Constants.id("block/magenta_stained_jar"));
        addNectarJarModels(resourceAdder, "light_blue_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/light_blue_stained_glass"), Constants.id("block/light_blue_stained_jar"));
        addNectarJarModels(resourceAdder, "yellow_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/yellow_stained_glass"), Constants.id("block/yellow_stained_jar"));
        addNectarJarModels(resourceAdder, "lime_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/lime_stained_glass"), Constants.id("block/lime_stained_jar"));
        addNectarJarModels(resourceAdder, "pink_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/pink_stained_glass"), Constants.id("block/pink_stained_jar"));
        addNectarJarModels(resourceAdder, "gray_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/gray_stained_glass"), Constants.id("block/gray_stained_jar"));
        addNectarJarModels(resourceAdder, "light_gray_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/light_gray_stained_glass"), Constants.id("block/light_gray_stained_jar"));
        addNectarJarModels(resourceAdder, "cyan_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/cyan_stained_glass"), Constants.id("block/cyan_stained_jar"));
        addNectarJarModels(resourceAdder, "purple_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/purple_stained_glass"), Constants.id("block/purple_stained_jar"));
        addNectarJarModels(resourceAdder, "blue_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/blue_stained_glass"), Constants.id("block/blue_stained_jar"));
        addNectarJarModels(resourceAdder, "brown_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/brown_stained_glass"), Constants.id("block/brown_stained_jar"));
        addNectarJarModels(resourceAdder, "green_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/green_stained_glass"), Constants.id("block/green_stained_jar"));
        addNectarJarModels(resourceAdder, "red_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/red_stained_glass"), Constants.id("block/red_stained_jar"));
        addNectarJarModels(resourceAdder, "black_stained_nectar_jar", ResourceLocation.fromNamespaceAndPath("minecraft", "block/black_stained_glass"), Constants.id("block/black_stained_jar"));
        
        ModCompatHandler.getModCompats().forEach(modCompat -> {
            if (modCompat instanceof JsonDefinedModCompatInstance jsonModCompat) {
                jsonModCompat.getModCompatJsonData().jars().forEach((jarData) -> {
                    var jarName = modCompat.getModid() + "_" + jarData.name() + "_nectar_jar";
                    resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("blockstates/" + jarName + ".json"), getNectarJarBlockstateJson(jarName));
                    addNectarJarModels(resourceAdder, jarName, jarData.blockTexture(), jarData.jarTexture());
                });
            }
        });
    }

    public static void addNectarJarModels(JsonDefinedModCompatInstance.ResourceAdder resourceAdder, String jarName, ResourceLocation blockTexture, ResourceLocation jarTexture) {
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + "_one_third.json"), getNecarJarModelText(false, 1, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + "_two_thirds.json"), getNecarJarModelText(false, 2, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + ".json"), getNecarJarModelText(false, 3, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + "_one_third_open.json"), getNecarJarModelText(true, 1, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + "_two_thirds_open.json"), getNecarJarModelText(true, 2, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/block/" + jarName + "_open.json"), getNecarJarModelText(true, 3, blockTexture, jarTexture));
        resourceAdder.addText(PackType.CLIENT_RESOURCES, Constants.id("models/item/" + jarName + ".json"), getParentedModelText(jarName));
    }
    
    public static String getNectarJarBlockstateJson(String id) {
        return String.format("""
                            {
                              "variants": {
                                "covered=false,level=1": {
                                  "model": "missingwilds:block/%1$s_one_third_open"
                                },
                                "covered=false,level=2": {
                                  "model": "missingwilds:block/%1$s_two_thirds_open"
                                },
                                "covered=false,level=3": {
                                  "model": "missingwilds:block/%1$s_open"
                                },
                                "covered=true,level=1": {
                                  "model": "missingwilds:block/%1$s_one_third"
                                },
                                "covered=true,level=2": {
                                  "model": "missingwilds:block/%1$s_two_thirds"
                                },
                                "covered=true,level=3": {
                                  "model": "missingwilds:block/%1$s"
                                }
                              }
                            }
                """, id);
    }

    public static String getNecarJarModelText(boolean isOpen, int level, ResourceLocation blockTexture, ResourceLocation jarTexture) {
        var open = isOpen ? "jar_open" : "jar";
        var parent = switch (level) {
            case 1 -> "missingwilds:block/template/bovines/one_third_nectar_" + open + "_template";
            case 2 -> "missingwilds:block/template/bovines/two_thirds_nectar_" + open + "_template";
            case 3 -> "missingwilds:block/template/bovines/nectar_" + open + "_template";
            default -> throw new IllegalStateException("Unexpected level value: " + level);
        };
        return String.format("""
                {
                  "parent": "%s",
                  "textures": {
                    "glass": "%s",
                    "jar": "%s"
                  }
                }
                """, parent, blockTexture, jarTexture);
    }
}
