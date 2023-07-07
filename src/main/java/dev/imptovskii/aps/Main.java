package dev.imptovskii.aps;
 
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketListener;
import dev.imptovskii.aps.commands.ReloadCommand;
import dev.imptovskii.aps.listeners.CommandBlocker;
import dev.imptovskii.aps.listeners.CustomVersion;
import dev.imptovskii.aps.listeners.SyntaxBlocker;
import dev.imptovskii.aps.packetadapters.AntiPluginStealer;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
 
public final class Main
   extends JavaPlugin
 {
   public void onEnable() {
     if (!(new File(getDataFolder(), "config.yml")).exists()) {
       getConfig().options().copyDefaults(true);
       saveDefaultConfig();
       getCommand("apsreload").setExecutor((CommandExecutor)new ReloadCommand(this));
     } 
     getServer().getPluginManager().registerEvents((Listener)new SyntaxBlocker(this), (Plugin)this);
     getServer().getPluginManager().registerEvents((Listener)new CommandBlocker(this), (Plugin)this);
     getServer().getPluginManager().registerEvents((Listener)new CustomVersion(this), (Plugin)this);
     Bukkit.getConsoleSender().sendMessage("AntiPluginStealer by JoseMarcellio, Fork by /new places/");
     if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
       
       if (!getServer().getVersion().contains("1.13") || !getServer().getVersion().contains("1.14") || !getServer().getVersion().contains("1.15") || !getServer().getVersion().contains("1.16") || !getServer().getVersion().contains("1.17") || !getServer().getVersion().contains("1.18") || !getServer().getVersion().contains("1.19") || !getServer().getVersion().contains("1.20")) {
         ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new AntiPluginStealer((Plugin)this, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }));
       }
     } else {
       
      getLogger().log(Level.WARNING, "Required ProtocolLib 5.0.0 or higher!");
     } 
   }
   
   public void onDisable() {
     if (getServer().getPluginManager().getPlugin("ProtocolLib") != null)
       ProtocolLibrary.getProtocolManager().removePacketListeners((Plugin)this); 
   }
 }
