package pl.trollcraft.SkyWarsDivisions.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    public static ItemStack buildItem(Material material, int amount){
        return new ItemStack(material, amount);
    }

    public static ItemStack buildItem(Material material, int amount, String name){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        name = ChatUtils.fixColor(name);
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack buildItem(Material material, int amount, String name, String lore){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        name = ChatUtils.fixColor(name);
        meta.setDisplayName(name);

        ArrayList<String> lore_list = new ArrayList<String>();
        lore = ChatUtils.fixColor(lore);
        lore_list.add(lore);
        meta.setLore(lore_list);

        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack buildItem(Material material, int amount, String name, ArrayList<String> lore){
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        name = ChatUtils.fixColor(name);
        meta.setDisplayName(name);

        ArrayList<String> lore_list = new ArrayList<String>();
        for(String s : lore){
            lore_list.add(ChatUtils.fixColor(s));
        }
        meta.setLore(lore_list);

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
