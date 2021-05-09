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
        MobMetadata.setDefaults(entity, this);
        this.location = entity.getLocation();
        this.entity.setCustomName("§7[Lvl. "+mobLevel+"] §c"+mobName+" §6"+mobHealth+"§b/"+mobType.getMaxHealth());
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

    public Location getLocation() { return this.location; }

    public void updateNametag() {
        entity.setCustomName("§7[Lvl. "+mobLevel+"] §c"+mobName+" §6"+mobHealth+"§b/"+mobType.getMaxHealth());
    }

    public void setHealth(int health) {
        if(health<=0) {
            entity.damage(entity.getHealth());
        }
        this.mobHealth = health;
        updateNametag();
        MobMetadata.setMobHealth(entity, health);
    }

    public void setArmor(List<ItemStack> armor) {
        this.armor = armor;
        MobMetadata.setMobArmor(entity, armor);

    }

}
