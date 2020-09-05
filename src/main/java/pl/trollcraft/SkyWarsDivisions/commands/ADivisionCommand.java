package pl.trollcraft.SkyWarsDivisions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.utils.ChatUtils;
import pl.trollcraft.SkyWarsDivisions.utils.Debug;

public class ADivisionCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            Debug.log("Komenda tylko dla graczy!");
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("division.admin")){
            ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
        }

        return true;
    }
}
