package pl.trollcraft.SkyWarsDivisions.playerDivision;

import pl.trollcraft.SkyWarsDivisions.division.Division;

public class PlayerDivision {

    private Division current_division;
    private int points;

    public PlayerDivision(Division current_division, int points){
        this.current_division = current_division;
        this.points = points;
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
}
