package pl.trollcraft.SkyWarsDivisions.division;

public class Division {

    private String name;
    private int min_points;
    private int max_points;

    public Division(String name, int min_points, int max_points){
        this.name = name;
        this.min_points = min_points;
        this.max_points = max_points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin_points() {
        return min_points;
    }

    public void setMin_points(int min_points) {
        this.min_points = min_points;
    }

    public int getMax_points() {
        return max_points;
    }

    public void setMax_points(int max_points) {
        this.max_points = max_points;
    }
}
