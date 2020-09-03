package pl.trollcraft.SkyWarsDivisions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.trollcraft.SkyWarsDivisions.utils.PlayerDivisionUtils;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        PlayerDivisionUtils.savePlayer(player);
    }
}
