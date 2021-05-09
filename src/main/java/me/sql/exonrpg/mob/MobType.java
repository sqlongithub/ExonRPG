package me.sql.exonrpg.mob;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum MobType {

    ZOMBIE("Zombie", EntityType.ZOMBIE, 60, 1);

    private String mobName;
    private EntityType type;
    private int mobMaxHealth;
    private int mobLevel;
    private List<ItemStack> armor;

    MobType(String mobName, EntityType type, int mobMaxHealth, int mobLevel, List<ItemStack> armor) {
        this.mobName = mobName;
        this.type = type;
        this.mobMaxHealth = mobMaxHealth;
        this.mobLevel = mobLevel;
        this.armor = armor;
    }

    MobType(String mobName, EntityType type, int mobHealth, int mobLevel) {
        this.mobName = mobName;
        this.type = type;
        this.mobMaxHealth = mobHealth;
        this.mobLevel = mobLevel;
        this.armor = new ArrayList<>();
    }

    public String getName() {
        return this.mobName;
    }
    public EntityType getEntityType() { return this.type; }
    public int getMaxHealth() { return this.mobMaxHealth; }
    public int getLevel() { return this.mobLevel; }
    public List<ItemStack> getArmor() { return this.armor; }

    public static boolean contains(MobType type) {
        try {
            valueOf(type.getName());
        } catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean contains(String type) {
        try {
            valueOf(type);
        } catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static MobType fromName(String name) {
        try {
            return valueOf(name);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }


}
