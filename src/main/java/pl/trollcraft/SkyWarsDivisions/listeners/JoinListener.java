package pl.trollcraft.SkyWarsDivisions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.trollcraft.SkyWarsDivisions.utils.PlayerDivisionUtils;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        PlayerDivisionUtils.loadPlayer(player);
    }
}
