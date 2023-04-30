package net.astrospud.hardlight.mixin;

import net.astrospud.hardlight.blocks.HLBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class HLAbstractBlockMixin {


	//WHY DOES THIS NOT GET CALLED
	@Inject(at = @At("HEAD"), method = "getCollisionShape", cancellable = true)
    public void hlgetCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        Entity entity = ((EntityShapeContext)context).getEntity();
        if (entity == null)
            return;
        if (state.isOf(HLBlocks.HARDLIGHT_BRIDGE) && !entity.isPlayer() && entity instanceof LivingEntity) {
            cir.setReturnValue(VoxelShapes.empty());
        }
    }

	/*//THIS WORTHLESS FUNCTION DOES NOTHING
	@Inject(at = @At("HEAD"), method = "onBlockCollision", cancellable = true)
	protected void hlonBlockCollision(BlockState state, CallbackInfo cir) {
		Entity entity = (Entity) (Object) this;
		if (state.isOf(HLBlocks.HARDLIGHT_BRIDGE)) {
			entity.setPosition(entity.getPos().add(0, -0.5, 0));
			HardlightMod.LOGGER.info("on block collision");
		}
	}*/
}
