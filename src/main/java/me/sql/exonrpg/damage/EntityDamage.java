package me.sql.exonrpg.damage;

import me.sql.exonrpg.ExonRPG;
import me.sql.exonrpg.mob.Mob;
import me.sql.exonrpg.mob.MobMetadata;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.metadata.FixedMetadataValue;

public class EntityDamage implements Listener {

    private void dealFallDamage(LivingEntity entity, int blocksFallen) {
        int mobHealth = entity.getMetadata(MobMetadata.MOB_HEALTH.toString()).get(0).asInt();
        entity.setMetadata(MobMetadata.MOB_HEALTH.toString(), new FixedMetadataValue(ExonRPG.plugin, mobHealth-(blocksFallen/2-1.5)*4));
    }

    private void dealDamage(Mob mob, LivingEntity damager) {
        int mobHealth = mob.getHealth();
        if(damager instanceof Player) {
            // TODO: Calculate player damage
            Player ply = (Player) damager;
            int damage = 5;
            mob.setHealth(mob.getHealth()-damage);
        } else {
            // TODO: Calculate mob Damage
            int damage = 5;
            mob.setHealth(mob.getHealth()-damage);
        }
    }

    @EventHandler
    public void onMobHitByPlayer(EntityDamageByEntityEvent e) {
        // Check if both entities are LivingEntities
        if(!(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof LivingEntity))
            return;

        LivingEntity vic = (LivingEntity) e.getEntity();
        LivingEntity damager = (LivingEntity) e.getDamager();

        // Check if victim is custom mob
        if(!(vic.getMetadata(MobMetadata.IS_CUSTOM_MOB.toString()).get(0).asBoolean()))
            return;

        dealDamage(MobMetadata.getMob(vic), damager);

    }

    @EventHandler
    public void onEntityTookFallDamage(EntityDamageEvent e) {
        if(!e.getCause().equals(DamageCause.FALL) || e.getEntity() instanceof LivingEntity)
            return;
        // TODO: Deal falldamage to mob from formula ((blocksfallen/2)-1.5)*4
        LivingEntity entity = (LivingEntity) e.getEntity();
        dealFallDamage(entity, Math.round(entity.getFallDistance()));


    }

}
