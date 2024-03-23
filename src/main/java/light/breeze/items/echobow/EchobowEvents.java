package light.breeze.items.echobow;

import light.breeze.CustomModelDatas;
import light.breeze.ManaSystem;
import light.breeze.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EchobowEvents implements Listener {

    private final ManaSystem mana;

    public EchobowEvents() {
        this.mana = new ManaSystem();
    }
    @EventHandler(priority= EventPriority.HIGH)
    public void onEntityShootBow(EntityShootBowEvent event) {
        LivingEntity shooter = event.getEntity();
        Boolean ignoreBecauseNotPlayer = false;
        Player player = null;

        if (shooter instanceof Player) {
            player = (Player) shooter;
        } else {
            ignoreBecauseNotPlayer = true;
        }
        if (ignoreBecauseNotPlayer||(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasCustomModelData() && event.getBow().getItemMeta().getCustomModelData() == CustomModelDatas.getCustomModelData("echo_bow"))) {
            if (!ignoreBecauseNotPlayer) {
                if ((player.getInventory().contains(Material.ENDER_PEARL)||event.getBow().containsEnchantment(Enchantment.SILK_TOUCH))&&this.mana.checkManaWarn(player)) {
                    if (!event.getBow().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        player.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL,1));
                        Utils.customDurability(event.getBow());
                        this.mana.addMana(player,-60);
                    }
                    EchoArrowProjTask projTask = new EchoArrowProjTask((Projectile) event.getProjectile());
                    projTask.runTaskTimer(Utils.getPlugin(),0,1);
                }
            }
        }
    }
}