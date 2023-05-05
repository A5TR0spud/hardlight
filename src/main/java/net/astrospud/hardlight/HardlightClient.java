package net.astrospud.hardlight;

import net.astrospud.hardlight.blocks.HLBlocks;
import net.astrospud.hardlight.blocks.entity.HardlightBridgeBlockEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;

@Environment(value= EnvType.CLIENT)
public class HardlightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HLBlocks.HARDLIGHT_BRIDGE, RenderLayer.getTranslucent());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int color = 0xffffff;
            if (world == null)
                return color;
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HardlightBridgeBlockEntity be) {
                color = be.getColor();
            }
            return color;
        }, HLBlocks.HARDLIGHT_BRIDGE);

        //ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> 0));

        /*dyeableBlocks.forEach((block) -> ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            int color = 16777215;

            BlockEntity blockEntity = view.getBlockEntity(pos);

            if (blockEntity != null && blockEntity instanceof HardlightBridgeBlockEntity)
                color = ((HardlightBridgeBlockEntity)blockEntity).getColor();

            return color;
        }, block.Block));

        dyeableBlocks.forEach((block) -> ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                ((DyeableBlockItem)stack.getItem()).getColor(stack), block.Item));*/
    }
}
