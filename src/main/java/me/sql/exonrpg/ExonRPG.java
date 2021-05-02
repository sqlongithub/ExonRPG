package me.sql.exonrpg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExonRPG extends JavaPlugin {

    public static void info(String msg) {
        Bukkit.getLogger().info("[ExonRPG Main] "+s);
    }

    @Override
    public void onEnable() {
        info("ExonRPG is an open-source MMORPG created by imSQL");
        info("and the projects contributors on github.com/sqlongithub/ExonRPG");
    }

    @Override
    public void onDisable() {

    }

}
