package me.sql.exonrpg.util.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.sql.exonrpg.util.Database;
import org.bukkit.command.CommandSender;

@CommandAlias("toggleautosave|tas|autosave")
@CommandPermission("exonrpg.database.autosave")
public class ToggleAutosaveCommand extends BaseCommand {

    @Default
    public void toggleAutosave(CommandSender sender) {
        Database.toggleAutosave();
    }

    @Subcommand("on")
    public void toggleAutosaveOn(CommandSender sender) {
        Database.toggleAutosave(true);
    }

    @Subcommand("off")
    public void toggleAutosaveOff(CommandSender sender) {
        Database.toggleAutosave(false);
    }

}
