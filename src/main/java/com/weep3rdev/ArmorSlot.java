package com.weep3rdev;

import net.runelite.api.EquipmentInventorySlot;

public enum ArmorSlot
{
    HEAD("Helmet", EquipmentInventorySlot.HEAD),
    BODY("Chest", EquipmentInventorySlot.BODY),
    LEGS("Legs", EquipmentInventorySlot.LEGS),
    BOOTS("Boots", EquipmentInventorySlot.BOOTS),
    GLOVES("Gloves", EquipmentInventorySlot.GLOVES),
    CAPE("Cape", EquipmentInventorySlot.CAPE),
    AMULET("Amulet", EquipmentInventorySlot.AMULET),
    WEAPON("Weapon", EquipmentInventorySlot.WEAPON),
    SHIELD("Shield", EquipmentInventorySlot.SHIELD),
    RING("Ring", EquipmentInventorySlot.RING);

    private final String displayName;
    private final EquipmentInventorySlot slot;

    ArmorSlot(String displayName, EquipmentInventorySlot slot)
    {
        this.displayName = displayName;
        this.slot = slot;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public EquipmentInventorySlot getSlot()
    {
        return slot;
    }
}