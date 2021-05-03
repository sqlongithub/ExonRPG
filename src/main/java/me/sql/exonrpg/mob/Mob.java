package me.sql.exonrpg.mob;

import me.sql.exonrpg.ExonRPG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Mob {

    private String mobName;
    private EntityType type;
    private int mobHealth;
    private int mobLevel;
    private List<ItemStack> armor;

    public Mob(String mobName, EntityType type, int mobHealth, int mobLevel, List<ItemStack> armor ) {
        this.mobName = mobName;
        this.type = type;
        this.mobHealth = mobHealth;
        this.mobLevel = mobLevel;
        this.armor = armor;
    }

    public static Mob fromType(MobType type) {
        return new Mob(type.getName(), type.getType(), type.getHealth(), type.getLevel(), type.getArmor());
    }

    public void spawnAtLocation(Location loc) {
        Entity e = loc.getWorld().spawnEntity(loc, type);
        if(!(e instanceof LivingEntity)) {
            ExonRPG.info("Mob of type "+type.toString()+" is not a LivingEntity!");
            return;
        }
        LivingEntity entity = (LivingEntity) e;
        entity.setMetadata(MobMetadata.IS_CUSTOM_MOB.toString(), new FixedMetadataValue(ExonRPG.plugin, true));
    }

}
