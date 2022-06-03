package dev.imptovskii.aps.listeners;

import dev.imptovskii.aps.Main;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
 
public class CommandBlocker implements Listener {
   private Main main;
   
   public CommandBlocker(Main main) {
     this.main = main;
   }
   @EventHandler
   public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
     for (String command : this.main.getConfig().getStringList("command-list")) {
       if ((e.getMessage().toLowerCase().equals("/" + command) || e.getMessage().toLowerCase().startsWith("/" + command + " ")) && 
         !e.getPlayer().hasPermission("antipluginstealer.command")) {
         e.setCancelled(true);
         String string = Objects.<String>requireNonNull(this.main.getConfig().getString("command-message"));
         if (string.equalsIgnoreCase("none"))
           return; 
         e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
       } 
     } 
  }
}
