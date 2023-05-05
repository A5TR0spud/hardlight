package net.astrospud.hardlight.blocks.custom;

import net.astrospud.hardlight.HardlightMod;
import net.astrospud.hardlight.blocks.HardlightSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HardlightBridgeItem extends BlockItem {
    private static final String BLOCK_ENTITY_TAG_KEY = "BlockEntityTag";
    public HardlightSettings settings = new HardlightSettings();
    public HardlightBridgeItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        NbtCompound nbt0 = stack.getOrCreateNbt();
        NbtCompound nbt1 = nbt0.getCompound(BLOCK_ENTITY_TAG_KEY);
        int color = 0xffffff;
        if (nbt0.contains(BLOCK_ENTITY_TAG_KEY) && nbt1.contains("color"))
            color = nbt1.getInt("color");
        tooltip.add(Text.literal(String.format("§l■§r§7#%06X", color)).setStyle(Style.EMPTY.withColor(color)));
        this.loadOrDefaultNbt(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //HardlightMod.LOGGER.info("use");
        if (world.isClient()) {

        }
        return super.use(world, user, hand);
    }


    public void loadOrDefaultNbt(ItemStack stack) {
        NbtCompound nbt0 = stack.getOrCreateNbt();
        NbtCompound nbt1 = nbt0.getCompound(BLOCK_ENTITY_TAG_KEY);

        NbtCompound nbt;

        if (nbt0.contains(BLOCK_ENTITY_TAG_KEY))
            nbt = nbt1;
        else
            nbt = new NbtCompound();

        if (!nbt1.contains("color")) {
            settings.setDefaultColor();
            nbt.putInt("color", settings.color);
        } else {
            settings.color = nbt1.getInt("color");
        }
        if (!nbt1.contains("defaultEntityCollide")) {
            settings.setDefaultEntityCollide();
            nbt.putBoolean("defaultEntityCollide", settings.defaultEntityCollide);
        } else {
            settings.defaultEntityCollide = nbt1.getBoolean("defaultEntityCollide");
        }
        if (!nbt1.contains("defaultPlayerCollide")) {
            settings.setDefaultPlayerCollide();
            nbt.putBoolean("defaultPlayerCollide", settings.defaultPlayerCollide);
        } else {
            settings.defaultPlayerCollide = nbt1.getBoolean("defaultPlayerCollide");
        }

        settings.setDefaultExceptEntity();
        if (!nbt1.contains("exceptEntities")) {
            NbtList exceptEntities = new NbtList();
            for (Identifier i : settings.exceptEntityCollide) {
                NbtString s = NbtString.of(i.toString());
                exceptEntities.add(s);
            }
            nbt.put("exceptEntities", exceptEntities);
        } else {
            NbtList exceptEntities = nbt1.getList("exceptEntities", NbtElement.STRING_TYPE);
            for (int i = 0; i < exceptEntities.size(); i++) {
                settings.exceptEntityCollide.add(Identifier.tryParse(exceptEntities.getString(i)));
            }
        }

        settings.setDefaultExceptPlayer();
        if (!nbt1.contains("exceptPlayers")) {
            NbtList exceptPlayers = new NbtList();
            for (UUID i : settings.exceptPlayerCollide) {
                NbtString s = NbtString.of(i.toString());
                exceptPlayers.add(s);
            }
            nbt.put("exceptPlayers", exceptPlayers);
        } else {
            NbtList exceptPlayers = nbt1.getList("exceptPlayers", NbtElement.STRING_TYPE);
            for (int i = 0; i < exceptPlayers.size(); i++) {
                settings.exceptPlayerCollide.add(UUID.fromString(exceptPlayers.getString(i)));
            }
        }

        nbt0.put(BLOCK_ENTITY_TAG_KEY, nbt);
    }

    /*@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        NbtCompound nbt0 = stack.getOrCreateNbt();
        defaultNbt(nbt0);
    }*/

    //I stole this from scaffolding lol
    @Override
    @Nullable
    public ItemPlacementContext getPlacementContext(ItemPlacementContext context) {
        Block block;
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(block = this.getBlock())) {
            Direction direction = context.shouldCancelInteraction() ? (context.hitsInsideBlock() ? context.getSide().getOpposite() : context.getSide()) : (context.getSide() == Direction.UP ? context.getPlayerFacing() : Direction.UP);
            int i = 0;
            BlockPos.Mutable mutable = blockPos.mutableCopy().move(direction);
            while (i < 7) {
                if (!world.isClient && !world.isInBuildLimit(mutable)) {
                    PlayerEntity playerEntity = context.getPlayer();
                    int j = world.getTopY();
                    if (!(playerEntity instanceof ServerPlayerEntity) || mutable.getY() < j) break;
                    ((ServerPlayerEntity)playerEntity).sendMessageToClient(Text.translatable("build.tooHigh", j - 1).formatted(Formatting.RED), true);
                    break;
                }
                blockState = world.getBlockState(mutable);
                if (!blockState.isOf(this.getBlock())) {
                    if (!blockState.canReplace(context)) break;
                    return ItemPlacementContext.offset(context, mutable, direction);
                }
                mutable.move(direction);
                if (!direction.getAxis().isHorizontal()) continue;
                ++i;
            }
            return null;
        }
        return context;
    }

    @Override
    protected boolean checkStatePlacement() {
        return false;
    }
}
