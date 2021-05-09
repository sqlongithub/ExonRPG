package me.sql.exonrpg.mob;

import me.sql.exonrpg.ExonRPG;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

public enum MobMetadata {

    // TODO: Optimize this by removing all the stats metadata and just having the mob

    IS_CUSTOM_MOB,
    MOB_NAME,
    MOB_HEALTH,
    MOB_ARMOR,
    MOB_LEVEL,
    MOB_TYPE,
    MOB;

    public static void setMobHealth(LivingEntity entity, int health) {
        entity.setMetadata(MOB_HEALTH.toString(), new FixedMetadataValue(ExonRPG.plugin, health));
    }

    public static void setMobName(LivingEntity entity, String name) {
        entity.setMetadata(MOB_NAME.toString(), new FixedMetadataValue(ExonRPG.plugin, name));
    }

    public static void setMobArmor(LivingEntity entity, List<ItemStack> armor) {
        entity.setMetadata(MOB_ARMOR.toString(), new FixedMetadataValue(ExonRPG.plugin, armor));
    }

    public static void setMobLevel(LivingEntity entity, int level) {
        entity.setMetadata(MOB_LEVEL.toString(), new FixedMetadataValue(ExonRPG.plugin, level));
    }

    public static void setMobType(LivingEntity entity, MobType type) {
        entity.setMetadata(MOB_TYPE.toString(), new FixedMetadataValue(ExonRPG.plugin, type));
    }

    public static void setDefaults(LivingEntity entity, Mob mob) {
        MobType type = mob.getMobType();
        entity.setMetadata(MobMetadata.IS_CUSTOM_MOB.toString(), new FixedMetadataValue(ExonRPG.plugin, true));
        entity.setMetadata(MobMetadata.MOB.toString(), new FixedMetadataValue(ExonRPG.plugin, mob));
        setMobHealth(entity, type.getMaxHealth());
        setMobLevel(entity, type.getLevel());
        setMobName(entity, type.getName());
        setMobType(entity, type);
        setMobArmor(entity, type.getArmor());
    }

    public static Mob getMob(LivingEntity entity) {
        return (Mob) entity.getMetadata(MOB.toString()).get(0);
    }

}

