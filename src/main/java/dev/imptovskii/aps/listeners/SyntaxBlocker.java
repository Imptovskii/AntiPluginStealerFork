 package dev.imptovskii.aps.listeners;

import dev.imptovskii.aps.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SyntaxBlocker
   implements Listener {
   private Main main;
   
   public SyntaxBlocker(Main main) {
     this.main = main;
   }
   @EventHandler
   public void onCommand(PlayerCommandPreprocessEvent e) {
     if (e.getMessage().split(" ")[0].contains(":") && !e.getPlayer().hasPermission("antipluginstealer.bypasssyntax")) {
       e.setCancelled(true);
       e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(this.main.getConfig().getString("antipluginstealer.prefix")) + this.main.getConfig().getString("antipluginstealer.syntaxblocker")));
     } 
   }
 }