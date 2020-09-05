package pl.trollcraft.SkyWarsDivisions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.trollcraft.SkyWarsDivisions.utils.ItemBuilder;

public class GlobalVariables {

    public static String skywars_tag = "&7[&bSkywars&7]&r ";
    public static int starting_points = 100;

    public static String division_rankup_text = "&aAwansujesz na range: &c%division%";
    public static String global_division_rankup_text = "&a&lGracz &c%player% &a&lawansowal na range &c%division%";

    public static String division_demote_text = "&aSpadles do rangi: &c%division%";

    public static String get_points_text = "&7Otrzymujesz &b%points% &7za zabojstwo";
    public static String lose_pooints_text = "&7Tracisz &c%points% &7za smierc";

    public static ItemStack fill_item = ItemBuilder.buildItem(Material.GRAY_STAINED_GLASS_PANE, 1);
    public static Material division_gui_material = Material.IRON_SWORD;
}
