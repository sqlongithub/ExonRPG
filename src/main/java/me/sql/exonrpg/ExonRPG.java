package me.sql.exonrpg;

import co.aikar.commands.PaperCommandManager;
import me.sql.exonrpg.damage.EntityDamage;
import me.sql.exonrpg.mob.command.SpawnMobCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ExonRPG extends JavaPlugin {

    // NOTE: Change the below values before publishing a release
    public static final String VERSION = "1.0";
    public static final String RELEASE_DATE = "" + "2/5/2021";

    public static Plugin plugin = Bukkit.getPluginManager().getPlugin("ExonRPG");
    public static void info(String msg) {
        Bukkit.getLogger().info("[ExonRPG] "+msg);
    }
    public static void error(String msg) {
        Bukkit.getLogger().severe("[ExonRPG] "+msg);
    }

    @Override
    public void onEnable() {
        info("ExonRPG is an open-source MMORPG created by imSQL");
        info("and the projects contributors on github.com/sqlongithub/ExonRPG.");
        info("You are currently running version "+VERSION+" ("+RELEASE_DATE+")");

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SpawnMobCommand());
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);

    }

    @Override
    public void onDisable() {

    }

}
