package net.astrospud.hardlight.blocks.entity.client;

import net.astrospud.hardlight.blocks.entity.HardlightBridgeBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class HardlightBridgeRenderer implements BlockEntityRenderer<HardlightBridgeBlockEntity> {
    public HardlightBridgeRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(HardlightBridgeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.pop();
    }
}
