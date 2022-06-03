package dev.imptovskii.aps.listeners;

import dev.imptovskii.aps.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
 
public class CustomVersion implements Listener {
   private Main main;
   
   public CustomVersion(Main main) {
     this.main = main;
   }
   @EventHandler
   public void execute(PlayerCommandPreprocessEvent event) {
     Player player = event.getPlayer();
     String command = event.getMessage().split(" ")[0];
     if (event.isCancelled())
       return; 
     if (!player.hasPermission("antipluginstealer.version") && 
       command.matches("(?i)/ver|/version|/bukkit:ver|/bukkit:version") && this.main.getConfig().getBoolean("custom-version.enabled")) {
       event.setCancelled(true);
       event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("custom-version.version-message")));
     } 
   }
 }
