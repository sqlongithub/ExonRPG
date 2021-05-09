package me.sql.exonrpg.player;

import me.sql.exonrpg.util.Database;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerStats {

    // Skills
    public static int[] xpRequired = new int[100];

    private UUID playerUUID;

    private int miningXp = 0;
    private int combatXp = 0;
    private int foragingXp = 0;
    private int agilityXp = 0;

    public PlayerStats(Player ply) {
        playerUUID = ply.getUniqueId();
        if(xpRequired[99]==0) {
            setXpRequired();
        }
    }

    public static void setXpRequired() {
        for(int i = 0; i<xpRequired.length; i++) {
            xpRequired[i] = (int) Math.round(22.5*(Math.pow(i+1, 2)));
        }
    }

    public int getMiningXp() {
        return miningXp;
    }
    public int getCombatXp() {
        return combatXp;
    }
    public int getForagingXp() {
        return foragingXp;
    }
    public int getAgilityXp() {
        return agilityXp;
    }

    public void addMiningXp(int amount) {
        miningXp+=amount;
    }
    public void addCombatXp(int amount) {
        combatXp+=amount;
    }
    public void addForagingXp(int amount) {
        foragingXp+=amount;
    }
    public void addAgilityXp(int amount) {
        agilityXp+=amount;
    }




}
