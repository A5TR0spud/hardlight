package net.astrospud.hardlight.blocks;

import net.astrospud.hardlight.HardlightMod;
import net.astrospud.hardlight.blocks.custom.HardlightBridgeBlock;
import net.astrospud.hardlight.blocks.custom.HardlightBridgeItem;
import net.astrospud.hardlight.blocks.custom.HardlightThrusterBlock;
import net.astrospud.hardlight.blocks.custom.HardlightWireBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class HLBlocks {
    public static Block HARDLIGHT_WIRE_BLOCK;
    public static Block HARDLIGHT_CIRCUIT_BLOCK;
    public static Block HARDLIGHT_BRIDGE;
    public static BlockItem HARDLIGHT_BRIDGE_ITEM;
    public static Block HARDLIGHT_THRUSTER;

    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(block, group, name);
        return Registry.register(Registry.BLOCK, new Identifier(HardlightMod.MODID, name), block);
    }

    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(HardlightMod.MODID, name), block);
    }

    public static Item registerBlockItem(Block block, ItemGroup group, String name) {
        return Registry.register(Registry.ITEM, new Identifier(HardlightMod.MODID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static Item registerBlockItem(BlockItem item, String name) {
        return Registry.register(Registry.ITEM, new Identifier(HardlightMod.MODID, name), item);
    }

    public static void init() {
        HardlightMod.LOGGER.info("registering blocks");
        HARDLIGHT_WIRE_BLOCK = registerBlock("hardlight_wire_block", new HardlightWireBlock(FabricBlockSettings.of(Material.GLASS)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 3)
                .allowsSpawning(HLBlocks::never)
                .emissiveLighting(HLBlocks::always)
                .postProcess(HLBlocks::always)
                .mapColor(MapColor.DARK_AQUA)), ItemGroup.BUILDING_BLOCKS);
        HARDLIGHT_THRUSTER = registerBlock("hardlight_thruster", new HardlightThrusterBlock(FabricBlockSettings.of(Material.GLASS)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 3)
                .allowsSpawning(HLBlocks::never)
                .emissiveLighting(HLBlocks::always)
                .postProcess(HLBlocks::always)
                .mapColor(MapColor.DARK_AQUA)), ItemGroup.BUILDING_BLOCKS);
        HARDLIGHT_CIRCUIT_BLOCK = registerBlock("hardlight_circuit_block", new HardlightWireBlock(FabricBlockSettings.of(Material.GLASS)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 3)
                .allowsSpawning(HLBlocks::never)
                .emissiveLighting(HLBlocks::always)
                .postProcess(HLBlocks::always)
                .mapColor(MapColor.DARK_AQUA)), ItemGroup.BUILDING_BLOCKS);
        HARDLIGHT_BRIDGE = registerBlock("hardlight_bridge", new HardlightBridgeBlock(FabricBlockSettings.of(Material.GLASS)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(state -> 3)
                .allowsSpawning(HLBlocks::never)
                .emissiveLighting(HLBlocks::always)
                .postProcess(HLBlocks::always)
                .nonOpaque()
                .slipperiness(1f)
                .solidBlock(HLBlocks::never)
                .suffocates(HLBlocks::never)
                .blockVision(HLBlocks::never)));
        HARDLIGHT_BRIDGE_ITEM = (BlockItem) registerBlockItem(new HardlightBridgeItem(HARDLIGHT_BRIDGE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)), "hardlight_bridge");
        HardlightMod.LOGGER.info("registered blocks");
    }

    private static boolean never(BlockState blockState, BlockView blockView, BlockPos pos) {
        return false;
    }

    private static boolean always(BlockState blockState, BlockView blockView, BlockPos pos) {
        return true;
    }

    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
}
