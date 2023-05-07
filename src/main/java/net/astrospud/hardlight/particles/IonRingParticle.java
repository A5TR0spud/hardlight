package net.astrospud.hardlight.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import java.util.function.Consumer;

public class IonRingParticle extends SpriteBillboardParticle {

    private static final Vec3f field_38334 = Util.make(new Vec3f(0.5f, 0.5f, 0.5f), Vec3f::normalize);
    private static final Vec3f field_38335 = new Vec3f(-1.0f, -1.0f, 0.0f);
    protected IonRingParticle(ClientWorld clientWorld,
                              double x, double y, double z,
                              SpriteProvider spriteSet,
                              double xd, double yd, double zd) {
        super(clientWorld, x, y, z, xd, yd, zd);
        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;
        this.velocityMultiplier = 1F;
        this.scale = 0.5f;
        this.maxAge = 20;
        this.gravityStrength = 0.0f;
        this.setSpriteForAge(spriteSet);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
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
            return new IonRingParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

    @Override
    public int getBrightness(float tint) {
        return 240;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        this.alpha = 1.0f - MathHelper.clamp(((float)this.age + tickDelta) / (float)this.maxAge, 0.0f, 1.0f);
        this.scale = 0.4f + 0.6f * ((float)age / (float)maxAge);
        double velX = this.velocityX;
        double velY = this.velocityY;
        double velZ = this.velocityZ;

        float yaw = (float) Math.atan2(velX, velZ);
        float pitch = (float) Math.atan2(-velY, Math.signum(velZ) * Math.sqrt(velX*velX + velZ*velZ));

        Quaternion q = Quaternion.fromEulerXyz(pitch, yaw, 0);

        this.buildGeometry(vertexConsumer, camera, tickDelta, quaternion -> {
            quaternion.set(q.getX(), q.getY(), q.getZ(), q.getW());
        });
        this.buildGeometry(vertexConsumer, camera, tickDelta, quaternion -> {
            quaternion.set(q.getX(), q.getY(), q.getZ(), q.getW());
            quaternion.hamiltonProduct(Vec3f.POSITIVE_Y.getRadialQuaternion((float)(-Math.PI)));
        });
    }

    private void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta, Consumer<Quaternion> rotator) {
        int j;
        Vec3d vec3d = camera.getPos();
        float f = (float)(MathHelper.lerp((double)tickDelta, this.prevPosX, this.x) - vec3d.getX());
        float g = (float)(MathHelper.lerp((double)tickDelta, this.prevPosY, this.y) - vec3d.getY());
        float h = (float)(MathHelper.lerp((double)tickDelta, this.prevPosZ, this.z) - vec3d.getZ());
        Quaternion quaternion = new Quaternion(field_38334, 0.0f, true);
        rotator.accept(quaternion);
        field_38335.rotate(quaternion);
        Vec3f[] vec3fs = new Vec3f[]{new Vec3f(-1.0f, -1.0f, 0.0f), new Vec3f(-1.0f, 1.0f, 0.0f), new Vec3f(1.0f, 1.0f, 0.0f), new Vec3f(1.0f, -1.0f, 0.0f)};
        float i = this.getSize(tickDelta);
        for (j = 0; j < 4; ++j) {
            Vec3f vec3f = vec3fs[j];
            vec3f.rotate(quaternion);
            vec3f.scale(i);
            vec3f.add(f, g, h);
        }
        j = this.getBrightness(tickDelta);
        this.vertex(vertexConsumer, vec3fs[0], this.getMaxU(), this.getMaxV(), j);
        this.vertex(vertexConsumer, vec3fs[1], this.getMaxU(), this.getMinV(), j);
        this.vertex(vertexConsumer, vec3fs[2], this.getMinU(), this.getMinV(), j);
        this.vertex(vertexConsumer, vec3fs[3], this.getMinU(), this.getMaxV(), j);
    }

    private void vertex(VertexConsumer vertexConsumer, Vec3f pos, float u, float v, int light) {
        vertexConsumer.vertex(pos.getX(), pos.getY(), pos.getZ()).texture(u, v).color(this.red, this.green, this.blue, this.alpha).light(light).next();
    }
}
