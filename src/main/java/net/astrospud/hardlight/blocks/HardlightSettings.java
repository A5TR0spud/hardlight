package net.astrospud.hardlight.blocks;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HardlightSettings {
    public boolean defaultEntityCollide;
    public List<Identifier> exceptEntityCollide = new ArrayList<>();;
    public boolean defaultPlayerCollide;
    public List<UUID> exceptPlayerCollide = new ArrayList<>();;
    public int color;

    public HardlightSettings() {
        /*exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.BOAT));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.CHEST_BOAT));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.CHEST_MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.COMMAND_BLOCK_MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.FURNACE_MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.HOPPER_MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.SPAWNER_MINECART));
        exceptEntityCollide.add(Registry.ENTITY_TYPE.getId(EntityType.TNT_MINECART));*/
    }


    public void setDefaults() {
        setDefaultEntityCollide();
        setDefaultExceptEntity();
        setDefaultPlayerCollide();
        setDefaultExceptPlayer();
        setDefaultColor();
    }

    public void setDefaultExceptEntity() {
        exceptEntityCollide = new ArrayList<>();
    }

    public void setDefaultExceptPlayer() {
        exceptPlayerCollide = new ArrayList<>();
    }

    public void setDefaultColor() {
        color = 0xffffff;
    }

    public void setDefaultEntityCollide() {
        defaultEntityCollide = false;
    }

    public void setDefaultPlayerCollide() {
        defaultEntityCollide = true;
    }
}
