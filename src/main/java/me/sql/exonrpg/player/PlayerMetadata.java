package me.sql.exonrpg.player;

import org.bukkit.entity.Player;

public enum PlayerMetadata {
    PLAYER_STATS;

    public static PlayerStats getPlayerStats(Player ply) {
        return (PlayerStats) ply.getMetadata(PLAYER_STATS.toString());
    }

}
