package pl.trollcraft.SkyWarsDivisions.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.trollcraft.SkyWarsDivisions.Main;
import pl.trollcraft.SkyWarsDivisions.division.Division;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;

import java.io.File;
import java.util.HashMap;

public class DivisionUtils {

    private static File file = new File(Main.getInstance().getDataFolder(), "divisions.yml");
    private static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
    private static HashMap<Integer, Division> divisions = new HashMap<Integer, Division>();

    public static void loadDivisions(){

        if(!configuration.contains("divisions")){
            return;
        }

        for(String key : configuration.getConfigurationSection("divisions").getKeys(false)){
            String division_name = configuration.getString("divisions." + key + ".name");
            int min_points = configuration.getInt("divisions." + key + ".min_points");
            int max_points = configuration.getInt("divisions." + key + ".max_points");

            divisions.put(min_points, new Division(division_name, min_points, max_points));
        }
    }

    public static Division findDivisionByPoints(int player_points){

        Division temp_division = null;

        for(int min_points : divisions.keySet()){
            if(min_points < player_points){
                temp_division = divisions.get(min_points);
                continue;
            }
            else{
                return temp_division;
            }
        }

        return null;
    }


    public static void setPlayerDivisionByPoints(PlayerDivision playerDivision){
        Division division = findDivisionByPoints(playerDivision.getPoints());
        playerDivision.setCurrent_division(division);
    }

}