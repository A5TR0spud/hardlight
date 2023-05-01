package net.astrospud.hardlight.blocks;

import net.astrospud.hardlight.HardlightMod;
import net.astrospud.hardlight.blocks.entity.HardlightBridgeBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class HLBlockEntities {
    public static BlockEntityType<HardlightBridgeBlockEntity> HARDLIGHT_BRIDGE;

    public static void init() {
        HardlightMod.LOGGER.info("registering block entities");
        HARDLIGHT_BRIDGE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(HardlightMod.MODID, "hardlight_bridge"),
                FabricBlockEntityTypeBuilder.create(HardlightBridgeBlockEntity::new,
                        HLBlocks.HARDLIGHT_BRIDGE).build(null));
        HardlightMod.LOGGER.info("registered block entities");
    }
}
