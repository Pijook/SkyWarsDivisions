package pl.trollcraft.SkyWarsDivisions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.trollcraft.SkyWarsDivisions.utils.PlayerDivisionUtils;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event){

        if(!(event.getEntity() instanceof Player)){
            return;
        }

        final Player dead = (Player) event.getEntity();
        final Player killer = dead.getKiller();

        PlayerDivisionUtils.calculateNewPoints(killer, dead);

    }
}
