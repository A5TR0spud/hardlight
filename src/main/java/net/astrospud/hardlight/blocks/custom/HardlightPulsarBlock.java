package net.astrospud.hardlight.blocks.custom;

import net.astrospud.hardlight.particles.HLParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class HardlightPulsarBlock extends Block {
    public static final DirectionProperty FACING = FacingBlock.FACING;
    public HardlightPulsarBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getSide());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        if (state.isOpaque() && blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
            return;
        }

        {
            double d = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetX() * 0.6;
            double e = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetY() * 0.6;
            double f = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + (double)direction.getOffsetZ() * 0.6;
            world.addParticle(ParticleTypes.ELECTRIC_SPARK,
                    (double) pos.getX() + d,
                    (double) pos.getY() + e,
                    (double) pos.getZ() + f,
                    4 * direction.getOffsetX() * (1 + 4 * random.nextFloat()) + (d - 0.5) * 2,
                    4 * direction.getOffsetY() * (1 + 4 * random.nextFloat()) + (e - 0.5) * 2,
                    4 * direction.getOffsetZ() * (1 + 4 * random.nextFloat()) + (f - 0.5) * 2
            );
        }

        if (random.nextInt(3) != 0)
            return;

        {
            double d = 0.5 + (double) direction.getOffsetX() * 0.6;
            double e = 0.5 + (double) direction.getOffsetY() * 0.6;
            double f = 0.5 + (double) direction.getOffsetZ() * 0.6;
            world.addParticle(HLParticles.ION_RING_PARTICLE,
                    (double) pos.getX() + d,
                    (double) pos.getY() + e,
                    (double) pos.getZ() + f,
                    0.05 * direction.getOffsetX(),
                    0.05 * direction.getOffsetY(),
                    0.05 * direction.getOffsetZ()
            );
        }
    }
}
