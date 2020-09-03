package pl.trollcraft.SkyWarsDivisions.utils;

import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.GlobalVariables;

public class ChatUtils {

    public static String fixColor(String message){
        message = message.replace("&","§");
        return message;
    }

    public static void sendMessage(Player player, String message){
        message = GlobalVariables.skywars_tag + message;
        player.sendMessage(fixColor(message));
    }

}
