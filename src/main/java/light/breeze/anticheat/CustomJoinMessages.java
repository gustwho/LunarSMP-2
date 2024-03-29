package light.breeze.anticheat;

import light.breeze.lang;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CustomJoinMessages implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin( PlayerJoinEvent event ) {
        event.setJoinMessage("");
        event.getPlayer().getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + event.getPlayer().getName() + " joined the server !!");
        event.getPlayer().sendMessage(lang.ascii_art_2);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeave( PlayerQuitEvent event ) {
        event.setQuitMessage("");
        event.getPlayer().getServer().broadcastMessage(ChatColor.DARK_PURPLE + event.getPlayer().getName() + " left the server..");
    }
}
