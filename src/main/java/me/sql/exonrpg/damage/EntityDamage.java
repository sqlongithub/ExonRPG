package me.sql.exonrpg.damage;

import me.sql.exonrpg.ExonRPG;
import me.sql.exonrpg.mob.Mob;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {

    private void dealFallDamage(Mob mob, int blocksFallen) {
        if(mob==null) {
            ExonRPG.error("Tried dealing fall damage to non custom mob. Make sure to check if the mob is custom before dealing fall damage to it");
            return;
        }
        mob.damage((int) ((blocksFallen/2-1.5)*4));
    }

    private void dealDamage(Mob mob, LivingEntity damager) {
        int damage;
        if(damager instanceof Player) {
            // TODO: Calculate player damage
            // Player ply = (Player) damager;
            damage = 5;
        } else {
            // TODO: Calculate mob Damage
            damage = 5;
        }
        mob.damage(damage);
    }

    @EventHandler
    public void onMobHitByPlayer(EntityDamageByEntityEvent e) {
        // Check if both entities are LivingEntities
        if(!(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof LivingEntity))
            return;
        // Check if damage was done by a plugin
        if(e.getCause().equals(DamageCause.CUSTOM))
            return;
        LivingEntity vic = (LivingEntity) e.getEntity();
        LivingEntity damager = (LivingEntity) e.getDamager();

        // Check if victim is custom mob
        Mob mob = ExonRPG.getMob(vic);
        if(mob==null)
            return;
        e.setDamage(0);
        dealDamage(mob, damager);

    }

    @EventHandler
    public void onEntityTookFallDamage(EntityDamageEvent e) {
        if(!(e.getCause().equals(DamageCause.FALL)  && e.getEntity() instanceof LivingEntity))
            return;
        LivingEntity entity = (LivingEntity) e.getEntity();
        Mob mob = ExonRPG.getMob(entity);
        if(mob==null)
            return;
        e.setDamage(0);
        // TODO: Deal falldamage to mob from formula ((blocksfallen/2)-1.5)*4
        dealFallDamage(mob, Math.round(entity.getFallDistance()));


    }

}
