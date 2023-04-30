package net.astrospud.hardlight.blocks.blockentities;

import net.astrospud.hardlight.blocks.HLBlockEntities;
import net.astrospud.hardlight.blocks.HardlightSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.UUID;

public class HardlightBridgeBlockEntity extends BlockEntity {
    public HardlightSettings settings = new HardlightSettings();

    public HardlightBridgeBlockEntity(BlockPos pos, BlockState state) {
        super(HLBlockEntities.HARDLIGHT_BRIDGE, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("red", settings.red);
        nbt.putFloat("green", settings.green);
        nbt.putFloat("blue", settings.blue);
        nbt.putBoolean("defaultEntityCollide", settings.defaultEntityCollide);
        nbt.putBoolean("defaultPlayerCollide", settings.defaultPlayerCollide);

        NbtList exceptEntities = new NbtList();
        for (Identifier i: settings.exceptEntityCollide) {
            NbtString s = NbtString.of(i.toString());
            exceptEntities.add(s);
        }
        nbt.put("exceptEntities", exceptEntities);

        NbtList exceptPlayers = new NbtList();
        for (UUID i: settings.exceptPlayerCollide) {
            NbtString s = NbtString.of(i.toString());
            exceptEntities.add(s);
        }
        nbt.put("exceptPlayers", exceptPlayers);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        settings.red = nbt.getFloat("red");
        settings.green = nbt.getFloat("green");
        settings.blue = nbt.getFloat("blue");
        settings.defaultEntityCollide = nbt.getBoolean("defaultEntityCollide");
        settings.defaultPlayerCollide = nbt.getBoolean("defaultPlayerCollide");

        settings.exceptEntityCollide = new ArrayList<>();
        NbtList exceptEntities = nbt.getList("exceptEntities", NbtElement.STRING_TYPE);
        for (int i = 0; i < exceptEntities.size(); i++) {
            settings.exceptEntityCollide.add(Identifier.tryParse(exceptEntities.getString(i)));
        }

        settings.exceptPlayerCollide = new ArrayList<>();
        NbtList exceptPlayers = nbt.getList("exceptPlayers", NbtElement.STRING_TYPE);
        for (int i = 0; i < exceptPlayers.size(); i++) {
            settings.exceptPlayerCollide.add(UUID.fromString(exceptPlayers.getString(i)));
        }
    }
}
