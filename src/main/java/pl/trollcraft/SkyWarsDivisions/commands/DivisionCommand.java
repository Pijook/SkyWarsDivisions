package pl.trollcraft.SkyWarsDivisions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.utils.ChatUtils;
import pl.trollcraft.SkyWarsDivisions.utils.Debug;
import pl.trollcraft.SkyWarsDivisions.utils.DivisionUtils;
import pl.trollcraft.SkyWarsDivisions.utils.PlayerDivisionUtils;

public class DivisionCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            Debug.log("Komenda tylko dla graczy!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("sprawdz")){
                PlayerDivisionUtils.showDivision(player, player);
                return true;
            }
            if(args[0].equalsIgnoreCase("lista")){
                DivisionUtils.showDivisionList(player);
                return true;
            }
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("sprawdz")){

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null){
                    ChatUtils.sendMessage(player, "&cTen gracz nie istnieje!");
                    return true;
                }

                PlayerDivisionUtils.showDivision(player, target);
                return true;
            }
        }

        ChatUtils.sendMessage(player, "&7/" + label + " sprawdz <nick>");
        ChatUtils.sendMessage(player, "&7/" + label + " lista");
        return true;
    }
}
