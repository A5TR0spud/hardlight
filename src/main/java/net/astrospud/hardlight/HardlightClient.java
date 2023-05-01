package net.astrospud.hardlight;

import net.astrospud.hardlight.blocks.HLBlockEntities;
import net.astrospud.hardlight.blocks.HLBlocks;
import net.astrospud.hardlight.blocks.entity.client.HardlightBridgeRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HardlightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HLBlocks.HARDLIGHT_BRIDGE, RenderLayer.getTranslucent());
        BlockEntityRendererRegistry.register(HLBlockEntities.HARDLIGHT_BRIDGE, HardlightBridgeRenderer::new);
    }
}
