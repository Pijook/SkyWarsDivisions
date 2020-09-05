package pl.trollcraft.SkyWarsDivisions.utils;

import com.google.gson.internal.LinkedTreeMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.Main;
import pl.trollcraft.SkyWarsDivisions.division.Division;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DivisionUtils {

    private static File file = new File(Main.getInstance().getDataFolder(), "divisions.yml");
    private static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
    private static LinkedTreeMap<Integer, Division> divisions = new LinkedTreeMap<Integer, Division>();

    public static void loadDivisions(){

        if(!configuration.contains("divisions")){
            return;
        }

        for(String key : configuration.getConfigurationSection("divisions").getKeys(false)){
            String division_name = configuration.getString("divisions." + key + ".name");
            int min_points = configuration.getInt("divisions." + key + ".min_points");
            int max_points = configuration.getInt("divisions." + key + ".max_points");
            boolean broadcast = configuration.getBoolean("divisions." + key + ".announce");

            divisions.put(min_points, new Division(division_name, min_points, max_points, broadcast));
        }
    }

    public static Division findDivisionByPoints(int player_points){

        Division temp_division;
        Debug.log("Player points: " + player_points);
        for(int min_points : divisions.keySet()){
            Debug.log("Min points: " + min_points);
            temp_division = divisions.get(min_points);
            if(min_points < player_points){
                Debug.log("Dywizja: " + temp_division.getName());
                Debug.log("Gracz ma wiecej");
                continue;
            }
            else{
                Debug.log("Zwracam: " + temp_division.getName());
                return temp_division;
            }
        }

        return null;
    }


    public static void setPlayerDivisionByPoints(PlayerDivision playerDivision){
        Division division = findDivisionByPoints(playerDivision.getPoints());
        playerDivision.setCurrent_division(division);
    }

    public static void showDivisionList(Player player){
        ChatUtils.sendMessage(player, "&7--------------");
        for(int key : divisions.keySet()){
            Division division = divisions.get(key);
            ChatUtils.sendMessage(player, "&7Nazwa: " + division.getName());
            ChatUtils.sendMessage(player, "&7Min Punktow: &c" + division.getMin_points());
            ChatUtils.sendMessage(player, "&7Max Punktow: &c" + division.getMax_points());
            ChatUtils.sendMessage(player, "&7--------------");
        }
    }

}
