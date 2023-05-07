package net.astrospud.hardlight.particles;

import net.astrospud.hardlight.HardlightMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HLParticles {
    public static final DefaultParticleType ION_RING_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType ION_PARTICLE = FabricParticleTypes.simple();

    public static void init() {
        HardlightMod.LOGGER.info("registering particles");
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(HardlightMod.MODID, "ion_ring"), ION_RING_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(HardlightMod.MODID, "ion"), ION_PARTICLE);
        HardlightMod.LOGGER.info("registered particles");
    }
}
