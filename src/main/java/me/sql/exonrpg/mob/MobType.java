package me.sql.exonrpg.mob;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum MobType {

    ZOMBIE("Zombie", EntityType.ZOMBIE, 60, 1);

    private String mobName;
    private EntityType type;
    private int mobHealth;
    private int mobLevel;
    private List<ItemStack> armor;

    MobType(String mobName, EntityType type, int mobHealth, int mobLevel, List<ItemStack> armor) {
        this.mobName = mobName;
        this.type = type;
        this.mobHealth = mobHealth;
        this.mobLevel = mobLevel;
        this.armor = armor;
    }

    MobType(String mobName, EntityType type, int mobHealth, int mobLevel) {
        this.mobName = mobName;
        this.type = type;
        this.mobHealth = mobHealth;
        this.mobLevel = mobLevel;
        this.armor = new ArrayList<>();
    }

    public String getName() {
        return this.mobName;
    }
    public EntityType getType() { return this.type; }
    public int getHealth() { return this.mobHealth; }
    public int getLevel() { return this.mobLevel; }
    public List<ItemStack> getArmor() { return this.armor; }
    public Mob newMob() { return Mob.fromType(this); }

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
