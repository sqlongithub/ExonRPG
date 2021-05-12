package me.sql.exonrpg;

import co.aikar.commands.PaperCommandManager;
import me.sql.exonrpg.damage.EntityDamage;
import me.sql.exonrpg.mob.Mob;
import me.sql.exonrpg.mob.command.SpawnMobCommand;
import me.sql.exonrpg.player.PlayerJoin;
import me.sql.exonrpg.util.command.ToggleAutosaveCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ExonRPG extends JavaPlugin {

    // NOTE: Change the below values before publishing a release
    public static final String VERSION = "1.0";
    public static final String RELEASE_DATE = "" + "2/5/2021";

    public static Map<LivingEntity, Mob> mobs = new HashMap<>();

    public static Plugin plugin;
    public static void info(String msg) {
        Bukkit.getLogger().info("[ExonRPG] "+msg);
    }
    public static void error(String msg) {
        Bukkit.getLogger().severe("[ExonRPG] "+msg);
    }

    @Override
    public void onEnable() {
        plugin = Bukkit.getPluginManager().getPlugin("ExonRPG");
        info("ExonRPG is an open-source MMORPG created by imSQL");
        info("and the projects contributors on github.com/sqlongithub/ExonRPG.");
        info("You are currently running version "+VERSION+" ("+RELEASE_DATE+")");

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SpawnMobCommand());
        manager.registerCommand(new ToggleAutosaveCommand());
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {

    }

    public static void storeMob(LivingEntity entity, Mob mob) {
        mobs.put(entity, mob);
    }

    public static Mob getMob(LivingEntity entity) {
        return mobs.get(entity);
    }

    public static void deleteMob(LivingEntity mob) {
        mobs.remove(mob);
    }

}
