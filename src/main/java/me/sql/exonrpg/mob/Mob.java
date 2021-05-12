package me.sql.exonrpg.mob;

import me.sql.exonrpg.ExonRPG;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Mob {

    private String mobName;
    private EntityType entityType;
    private MobType mobType;
    private int mobHealth;
    private int mobLevel;
    private List<ItemStack> armor;
    private LivingEntity entity;
    private Location location;

    public Mob(MobType mobType, Location loc) {
        this.mobName = mobType.getName();
        this.entityType = mobType.getEntityType();
        this.mobHealth = mobType.getMaxHealth();
        this.mobLevel = mobType.getLevel();
        this.armor = mobType.getArmor();
        this.mobType = mobType;

        Entity e = loc.getWorld().spawnEntity(loc, entityType);
        if(!(e instanceof LivingEntity)) {
            ExonRPG.info("Mob of type "+entityType.toString()+" is not a LivingEntity!");
            return;
        }
        entity = (LivingEntity) e;
        this.location = entity.getLocation();
        ExonRPG.storeMob(entity, this);
        updateNametag();
        entity.setCustomNameVisible(true);
    }


    public String getName() {
        return this.mobName;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public MobType getMobType() {
        return this.mobType;
    }

    public int getHealth() {
        return this.mobHealth;
    }

    public int getLevel() {
        return this.mobLevel;
    }

    public List<ItemStack> getArmor() {
        return this.armor;
    }

    public Location getLocation() {
        return this.location;
    }

    public LivingEntity getMobEntity() {
        return this.entity;
    }

    public void updateNametag() {
        if(mobHealth<=mobType.getMaxHealth()*0.4)
            entity.setCustomName("§7[Lvl. "+mobLevel+"] §6"+mobName+" §c"+mobHealth+"§7/§a"+mobType.getMaxHealth());
        else
            entity.setCustomName("§7[Lvl. "+mobLevel+"] §6"+mobName+" §a"+mobHealth+"§7/§a"+mobType.getMaxHealth());
    }

    public void damage(int damage) {
        if(damage>=mobHealth) {
            kill();
            return;
        }
        entity.damage(1);
        setHealth(mobHealth-damage);
    }

    public void kill() {
        mobHealth = 0;
        updateNametag();
        entity.damage(entity.getHealth());
        ExonRPG.deleteMob(entity);
    }

    public void setHealth(int health) {
        if(health<=0) {
            kill();
        }
        mobHealth = health;
        updateNametag();
    }

    public void setArmor(List<ItemStack> armor) {
        this.armor = armor;
    }

}
