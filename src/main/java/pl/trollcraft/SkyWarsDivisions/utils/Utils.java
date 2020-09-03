package pl.trollcraft.SkyWarsDivisions.utils;


public class Utils {

    public static boolean isInteger(final String a) {
        try {
            Integer.parseInt(a);
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean isDouble(final String a) {
        try {
            Double.parseDouble(a);
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
