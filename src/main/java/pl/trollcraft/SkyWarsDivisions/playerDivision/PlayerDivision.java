package pl.trollcraft.SkyWarsDivisions.playerDivision;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.trollcraft.SkyWarsDivisions.GlobalVariables;
import pl.trollcraft.SkyWarsDivisions.division.Division;
import pl.trollcraft.SkyWarsDivisions.utils.ChatUtils;
import pl.trollcraft.SkyWarsDivisions.utils.Debug;
import pl.trollcraft.SkyWarsDivisions.utils.DivisionUtils;

public class PlayerDivision {

    private Player player;
    private Division current_division;
    private int points;
    private int kills;
    private int deaths;
    private float kdr;
    public PlayerDivision(Player player, Division current_division, int points, int kills, int deaths){
        this.player = player;
        this.current_division = current_division;
        this.points = points;
        this.kills = kills;
        this.points = deaths;
        countKDR();
    }

    public void countKDR(){
        this.kdr = this.kills / this.deaths;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Division getCurrent_division() {
        return current_division;
    }

    public void setCurrent_division(Division current_division) {
        this.current_division = current_division;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public float getKdr() {
        return kdr;
    }

    public void setKdr(float kdr) {
        this.kdr = kdr;
    }

    public void addPoints(int amount){
        this.points = points + amount;
        if(this.points > this.current_division.getMax_points()){
            this.current_division = DivisionUtils.findDivisionByPoints(this.points);
            ChatUtils.sendMessage(this.player, GlobalVariables.division_rankup_text.replace("%division%", current_division.getName()));

            if(current_division.isBroadcast()){
                ChatUtils.broadcast(GlobalVariables.global_division_rankup_text.replace("%player%", player.getName()).replace("%division%", current_division.getName()));
            }
        }
    }

    public void removePoints(int amount){
        this.points = points - amount;

        if(this.points < this.current_division.getMin_points()){
            this.current_division = DivisionUtils.findDivisionByPoints(this.points);
            ChatUtils.sendMessage(this.player, GlobalVariables.division_demote_text.replace("%division%", current_division.getName()));
        }
    }

    public void resetPoints(){
        this.points = GlobalVariables.starting_points;
    }

    public void addDeath(){
        this.deaths = deaths + 1;
    }

    public void addKill(){
        this.kills = kills + 1;
    }

    public void resetDeath(){

    }
}
