package light.breeze.items.burningaxe;

import light.breeze.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class BurningAxeEvents implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( PlayerInteractEvent event ) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        Map<Integer, String> m = new HashMap<>();
        m.put(0, "Fireball");
        m.put(1, "Machine Gun");
        m.put(2, "Enderdragon");
        if (Utils.checkIfMeta(hand)) {
            int cmd = hand.getItemMeta().getCustomModelData();
            int type = cmd - 9008000;
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (cmd > 9008000 && cmd < 9009000) {
                    if (player.isSneaking()) {
                        type = ( type + 1 ) % 3;
                        player.sendTitle("", "Ability set to: " + m.get(type), 5, 5, 5);
                        ItemMeta meta = hand.getItemMeta();
                        meta.setCustomModelData(( type + 1 ) + 9008000);
                        hand.setItemMeta(meta);
                    } else if (! player.hasCooldown(hand.getType()) && player.getCooldown(hand.getType()) < 1) {
                        Entity proj = null;

                        if (type == 2) {
                            proj = player.launchProjectile(SmallFireball.class);
                            player.setCooldown(hand.getType(), 10);
                        } else if (type == 3) {
                            proj = player.launchProjectile(DragonFireball.class);
                            player.setCooldown(hand.getType(), 120);
                        } else {
                            proj = player.launchProjectile(Fireball.class);
                            player.setCooldown(hand.getType(), 60);
                        }
                        player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, SoundCategory.MASTER, 3, 0.75f);
                        proj.setVelocity(player.getLocation().getDirection().multiply(4));
                    }
                }
            }
        }
    }
}