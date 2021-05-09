package me.sql.exonrpg.mob.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Single;
import me.sql.exonrpg.mob.Mob;
import me.sql.exonrpg.mob.MobType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("spawnmob")
@CommandPermission("exonrpg.spawnmob")
public class SpawnMobCommand extends BaseCommand {

    @Default
    public void summonMob(Player ply, @Single String mobName) {
        if(mobName == null) {
            ply.sendMessage("§cPlease supply a mob name, i.e ZOMBIE");
            return;
        }
        try {
            Location spawnLoc = ply.getTargetBlock(2).getRelative(ply.getTargetBlockFace(2)).getLocation();
            MobType type = MobType.fromName(mobName);
            if(type==null) {
                ply.sendMessage("§cThis mob type is invalid.");
                return;
            }
            Mob mob = new Mob(type, spawnLoc);
        } catch(NullPointerException e) {
            ply.sendMessage("§cFatal Error trying to get the block you're looking at!");
            return;
        }
    }

}
