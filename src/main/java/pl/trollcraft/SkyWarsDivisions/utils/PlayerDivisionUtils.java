package pl.trollcraft.SkyWarsDivisions.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.trollcraft.SkyWarsDivisions.GlobalVariables;
import pl.trollcraft.SkyWarsDivisions.Main;
import pl.trollcraft.SkyWarsDivisions.playerDivision.PlayerDivision;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerDivisionUtils {

    private static final File file = new File(Main.getInstance().getDataFolder(), "players_divisions.yml");
    private static final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    public static void loadPlayer(Player player){
        if(!configuration.contains("players." + player.getName())){
            Debug.log("Nie ma gracza w configu! Tworze nowego");
            Main.playersDivisions.put(player, new PlayerDivision(player, DivisionUtils.findDivisionByPoints(100), 100, 0, 0));
            Debug.log("Utworzono nowego gracza: ");
            PlayerDivision playerDivision = Main.playersDivisions.get(player);
            Debug.log("Punkty: " + playerDivision.getPoints());
            Debug.log("KDR: " + playerDivision.getKdr());
            Debug.log("Dywizja: " + playerDivision.getCurrent_division().getName());
            return;
        }

        int player_points = configuration.getInt("players." + player.getName() + ".division_points");
        int player_kills = configuration.getInt("players." + player.getName() + ".division_kills");
        int player_deaths = configuration.getInt("players." + player.getName() + ".division_deaths");
        Main.playersDivisions.put(player, new PlayerDivision(player, DivisionUtils.findDivisionByPoints(player_points), player_points, player_kills, player_deaths));

    }

    public static boolean isPlayerInConfig(Player player){
        return configuration.contains("players." + player.getName());
    }

    public static PlayerDivision loadOfflinePlayer(Player player){
        int player_points = configuration.getInt("players." + player.getName() + ".division_points");
        int player_kills = configuration.getInt("players." + player.getName() + ".division_kills");
        int player_deaths = configuration.getInt("players." + player.getName() + ".division_deaths");

        return new PlayerDivision(player, DivisionUtils.findDivisionByPoints(player_points), player_points, player_kills, player_deaths);
    }

    public static void savePlayer(Player player){
        PlayerDivision playerDivision = Main.playersDivisions.get(player);

        Debug.log("Zapisuje gracza: " + player.getName());
        Debug.log("Punkty: " + playerDivision.getPoints());
        Debug.log("KDR: " + playerDivision.getKdr());
        Debug.log("Dywizja: " + playerDivision.getCurrent_division().getName());

        configuration.set("players." + player.getName() + ".division_points", playerDivision.getPoints());
        configuration.set("players." + player.getName() + ".division_kills", playerDivision.getKills());
        configuration.set("players." + player.getName() + ".division_deaths", playerDivision.getDeaths());

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

    public static int countPointsDifference(PlayerDivision killer, PlayerDivision dead){
        int points = killer.getPoints() - dead.getPoints();

        if(points < 0){
            points = points * -1;
        }

        return points;
    }

    public static void calculateNewPoints(Player killer, Player dead){
        PlayerDivision killerDivision = Main.playersDivisions.get(killer);
        PlayerDivision deadDivision = Main.playersDivisions.get(dead);

        int points_difference = countPointsDifference(killerDivision, deadDivision);

        int multiplier = points_difference / (points_difference / 10);
        Debug.log("Multiplier: " + multiplier);

        float deadKDR = deadDivision.getKdr();

        if(deadKDR < 0.3){
            deadKDR = 1;
        }

        float killerKDR = killerDivision.getKdr();

        int deadPoints = (int) (multiplier * killerKDR);
        int killerPoints = (int) (multiplier * deadKDR);

        if(deadDivision.getPoints() <= (GlobalVariables.starting_points / 2)){
            deadPoints = 0;
        }

        killerDivision.addPoints(killerPoints);
        deadDivision.removePoints(deadPoints);

        ChatUtils.sendMessage(killer, GlobalVariables.get_points_text.replace("%points%", String.valueOf(killerPoints)));
        ChatUtils.sendMessage(dead, GlobalVariables.lose_pooints_text.replace("%points%", String.valueOf(deadPoints)));

        killerDivision.addKill();
        killerDivision.countKDR();
        deadDivision.addDeath();
        deadDivision.countKDR();
    }

    public static void showDivision(Player player, Player target){
        PlayerDivision playerDivision;

        if(!target.isOnline()){
            if(!isPlayerInConfig(target)){
                ChatUtils.sendMessage(player, "&cNie odnaleziono danych gracza!");
                return;
            }
            else{
                playerDivision = loadOfflinePlayer(target);
            }
        }
        else{
            playerDivision = Main.playersDivisions.get(target);
        }

        Inventory inventory = Bukkit.createInventory(null, 9, "Dywizja");

        for(int i = 0; i < 9; i++){
            inventory.setItem(i, GlobalVariables.fill_item);
        }

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("&7Obecna dywizja: " + playerDivision.getCurrent_division().getName());
        lore.add("&7KDR: &c" + playerDivision.getKdr());

        ItemStack playerSkull = ItemBuilder.buildItem(GlobalVariables.division_gui_material, 1, "&a&l" + target.getName(), lore);

        inventory.setItem(4, playerSkull);

        player.openInventory(inventory);
    }
}
