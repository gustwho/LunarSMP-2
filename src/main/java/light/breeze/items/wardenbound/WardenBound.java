package light.breeze.items.wardenbound;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WardenBound {
    public ItemStack createWardenBound() {
        ItemStack wardenBound = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = wardenBound.getItemMeta();
        meta.setCustomModelData(9002002);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Wardenbound Sword");
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "In the age of legends, a brave warrior killed a mighty warden.");
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "This is the spirit of that warden, infused into this very sword.");
        lore.add("");
        lore.add(ChatColor.GRAY + "When Used:");
        lore.add(ChatColor.GREEN + "Shoots Warden Ray");
        meta.setLore(lore);
        wardenBound.setItemMeta(meta);
        return wardenBound;
    }
}