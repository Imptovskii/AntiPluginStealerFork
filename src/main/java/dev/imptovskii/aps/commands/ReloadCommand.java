package dev.imptovskii.aps.commands;
 
import dev.imptovskii.aps.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand
   implements CommandExecutor {
   private Main main;
   
   public ReloadCommand(Main main) {
     this.main = main;
   }
   
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     CommandSender s = sender;
     if (args.length == 0) {
       if (s.hasPermission("antipluginstealer.reload")) {
         this.main.reloadConfig();
         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(this.main.getConfig().getString("antipluginstealer.prefix")) + this.main.getConfig().getString("antipluginstealer.reload-message")));
       } else {
         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(this.main.getConfig().getString("antipluginstealer.prefix")) + this.main.getConfig().getString("antipluginstealer.no-permission")));
       } 
     }
     return true;
   }
 }


/* Location:              D:\Downloads\AntiPluginStealer.jar!\com\josemarcellio\joseantipluginstealer\commands\JoseReloadCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */