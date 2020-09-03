package pl.trollcraft.SkyWarsDivisions;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import me.wazup.skywars.PlaceholderAPIHooks;
import me.wazup.skywars.PlayerData;
import me.wazup.skywars.Skywars;
import me.wazup.skywars.SkywarsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.trollcraft.SkyWarsDivisions.listeners.JoinListener;
import pl.trollcraft.SkyWarsDivisions.listeners.QuitListener;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;
import pl.trollcraft.SkyWarsDivisions.utils.Debug;
import pl.trollcraft.SkyWarsDivisions.utils.DivisionUtils;

import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main instance;
    private static SkywarsAPI skywarsAPI;

    public static HashMap<Player, PlayerDivision> playersDivisions = new HashMap<Player, PlayerDivision>();

    @Override
    public void onEnable() {
        instance = this;

        if(!getServer().getPluginManager().isPluginEnabled("Skywars")){
            Debug.log("&cPlugin na Skywars nie zostal wlaczony!");
            Debug.log("&cDywizje nie beda dzialac!");
        }

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        PlaceholderAPI.registerPlaceholderHook(this, new Placeholders());

        DivisionUtils.loadDivisions();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static SkywarsAPI getSkywarsAPI() {
        return skywarsAPI;
    }
}
