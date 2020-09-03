package pl.trollcraft.SkyWarsDivisions;

import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;

public class Placeholders extends PlaceholderHook {

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        PlayerDivision playerDivision = Main.playersDivisions.get(player);

        if(params.equalsIgnoreCase("division")){
            return playerDivision.getCurrent_division().getName();
        }

        return super.onPlaceholderRequest(player, params);
    }
}
