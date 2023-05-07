package net.astrospud.hardlight.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import java.util.function.Consumer;

public class IonParticle extends SpriteBillboardParticle {
    protected IonParticle(ClientWorld clientWorld,
                          double x, double y, double z,
                          SpriteProvider spriteSet,
                          double xd, double yd, double zd) {
        super(clientWorld, x, y, z, xd, yd, zd);
        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;
        this.velocityMultiplier = 1F;
        this.scale = 0.05f;
        this.maxAge = 20;
        this.gravityStrength = 0.0f;
        this.setSpriteForAge(spriteSet);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = 1.0f - MathHelper.clamp(((float)this.age) / (float)this.maxAge, 0.0f, 1.0f);
        this.scale = 0.05f - 0.049f * ((float)age / (float)maxAge);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new IonParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

    @Override
    public int getBrightness(float tint) {
        return 240;
    }
}
