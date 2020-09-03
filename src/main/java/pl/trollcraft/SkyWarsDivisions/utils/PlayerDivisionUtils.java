package pl.trollcraft.SkyWarsDivisions.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.Main;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;

import java.io.File;
import java.io.IOException;

public class PlayerDivisionUtils {

    private static final File file = new File(Main.getInstance().getDataFolder(), "players_divisions.yml");
    private static final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    public static void loadPlayer(Player player){
        if(!configuration.contains("players." + player.getName())){
            Main.playersDivisions.put(player, new PlayerDivision(DivisionUtils.findDivisionByPoints(100), 100));
            return;
        }

        int player_points = configuration.getInt("players." + player.getName() + ".division_points");
        Main.playersDivisions.put(player, new PlayerDivision(DivisionUtils.findDivisionByPoints(player_points), 0));

    }

    public static void savePlayer(Player player){
        int player_points = Main.playersDivisions.get(player).getPoints();

        configuration.set("players." + player.getName() + ".division_points", player_points);

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.playersDivisions.remove(player);
    }

    public static boolean canRankUp(Player player){
        PlayerDivision playerDivision = Main.playersDivisions.get(player);
        return playerDivision.getPoints() == playerDivision.getCurrent_division().getMax_points();
    }
}
