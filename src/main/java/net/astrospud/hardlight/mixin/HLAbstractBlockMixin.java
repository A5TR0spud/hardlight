package net.astrospud.hardlight.mixin;

import net.astrospud.hardlight.blocks.HLBlockEntities;
import net.astrospud.hardlight.blocks.HLBlocks;
import net.astrospud.hardlight.blocks.blockentities.HardlightBridgeBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AbstractBlock.class)
public class HLAbstractBlockMixin {
    @Inject(at = @At("HEAD"), method = "getCollisionShape", cancellable = true)
    public void hlgetCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        Entity entity = ((EntityShapeContext)context).getEntity();
        if (entity == null)
            return;
        if (state.isOf(HLBlocks.HARDLIGHT_BRIDGE)) {
            Optional<HardlightBridgeBlockEntity> op = world.getBlockEntity(pos, HLBlockEntities.HARDLIGHT_BRIDGE);
            if (op.isEmpty())
                return;
            HardlightBridgeBlockEntity be = op.get();
            if (entity.isPlayer()) {
                if (be.settings.exceptPlayerCollide.contains(entity.getUuid()) == be.settings.defaultPlayerCollide)
                    cir.setReturnValue(VoxelShapes.empty());
            } else if (be.settings.exceptEntityCollide.contains(Registry.ENTITY_TYPE.getId(entity.getType())) == be.settings.defaultEntityCollide){
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }
}
