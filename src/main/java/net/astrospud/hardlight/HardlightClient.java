package net.astrospud.hardlight;

import net.astrospud.hardlight.blocks.HLBlocks;
import net.astrospud.hardlight.blocks.entity.HardlightBridgeBlockEntity;
import net.astrospud.hardlight.particles.HLParticles;
import net.astrospud.hardlight.particles.IonRingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
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

        ParticleFactoryRegistry.getInstance().register(HLParticles.ION_RING_PARTICLE, IonRingParticle.Factory::new);
    }
}
