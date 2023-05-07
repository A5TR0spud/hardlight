package net.astrospud.hardlight.blocks.entity;

import net.astrospud.hardlight.blocks.HLBlockEntities;
import net.astrospud.hardlight.blocks.HardlightSettings;
import net.astrospud.hardlight.blocks.custom.HardlightPulsarBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HardlightPulsarBlockEntity extends BlockEntity {
    public HardlightPulsarBlockEntity(BlockPos pos, BlockState state) {
        super(HLBlockEntities.HARDLIGHT_PULSAR, pos, state);
    }

    public static <E extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, E e) {
        Direction dir = state.get(HardlightPulsarBlock.FACING);

        int maxDistance = 10;
        double blowStrength = 0.045;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int dx = dir.getOffsetX() * maxDistance + 1;
        int dy = dir.getOffsetY() * maxDistance + 1;
        int dz = dir.getOffsetZ() * maxDistance + 1;
        List<Entity> entities = world.getOtherEntities(null, new Box(x, y, z, x+dx, y+dy, z+dz));

        for (Entity a : entities) {
            if (a instanceof PlayerEntity player && (player.getAbilities().flying || player.isSpectator()))
                continue;
            double strength = Math.max(maxDistance - Math.sqrt(a.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ())), 0) / maxDistance;
            a.addVelocity(
                    dir.getOffsetX() * blowStrength * strength,
                    dir.getOffsetY() * blowStrength * strength,
                    dir.getOffsetZ() * blowStrength * strength
            );
        }
    }
}
