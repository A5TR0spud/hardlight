package net.astrospud.hardlight.blocks;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HardlightSettings {
    public boolean defaultEntityCollide = false;
    public List<Identifier> exceptEntityCollide = new ArrayList<>();
    public boolean defaultPlayerCollide = true;
    public List<UUID> exceptPlayerCollide = new ArrayList<>();
    public float red = 1f;
    public float green = 1f;
    public float blue = 1f;

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
}
