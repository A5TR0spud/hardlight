package net.astrospud.hardlight.blocks.custom;

import net.astrospud.hardlight.blocks.HLBlockEntities;
import net.astrospud.hardlight.blocks.HLBlocks;
import net.astrospud.hardlight.blocks.entity.HardlightBridgeBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HardlightBridgeBlock extends BlockWithEntity implements BlockEntityProvider {
    public HardlightBridgeBlock(Settings settings) {
        super(settings);
    }

    private static VoxelShape SHAPE = Block.createCuboidShape(0, 14, 0, 16, 16, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().isOf(this.asItem());
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }


    /* BLOCK ENTITY GARBAGE BELOW THIS LINE */

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Entity entity = ((EntityShapeContext)context).getEntity();
        if (entity == null)
            return super.getCollisionShape(state, world, pos, context);
        Optional<HardlightBridgeBlockEntity> op = world.getBlockEntity(pos, HLBlockEntities.HARDLIGHT_BRIDGE);
        if (op.isEmpty())
            return super.getCollisionShape(state, world, pos, context);
        HardlightBridgeBlockEntity be = op.get();
        if (entity.isPlayer()) {
            if (be.settings.exceptPlayerCollide.contains(entity.getUuid()) == be.settings.defaultPlayerCollide)
                return VoxelShapes.empty();
        } else if (be.settings.exceptEntityCollide.contains(Registry.ENTITY_TYPE.getId(entity.getType())) == be.settings.defaultEntityCollide){
            return VoxelShapes.empty();
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    /*@Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }*/

    /*@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }*/

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HardlightBridgeBlockEntity(pos, state);
    }
}
