package com.drake.hub;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HubMain extends JavaPlugin implements Listener {
	/*D�marrage Plugin*/
	public static HubMain plugin;
	public void onEnable(){
		EventManager.RegisterEvent(this);
		this.getConfig().options().copyDefaults(true);
        saveConfig();
	}
	/*Commandes*/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    final Player p = (Player) sender;
	    /*D�finir le lobby*/
		if(cmd.getName().equalsIgnoreCase("setlobby")){
			if(sender instanceof Player){
				if(!p.hasPermission("hub.set.lobby")){p.sendMessage("Tu n'as pas la permission");}
				if(p.hasPermission("hub.set.lobby")){
					Location hub = p.getLocation();
					String path = "lobby";
					this.getConfig().set(path, hub);
					this.saveConfig();
				}
			}
		/*T�l�porte au lobby*/
		}else if(cmd.getName().equalsIgnoreCase("lobby")){
			p.teleport((Location) this.getConfig().get("lobby"));
			p.sendMessage(ChatUtils.getGamePrefix()+ ChatColor.GRAY + "T�l�portation vers le lobby");
		}
		return false;
	}
	
	
	/*Event du player qui rejoint*/
	@EventHandler
	public void playerLogin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		/*Marche pas*/p.teleport((Location) this.getConfig().get("lobby"));
		//p.getInventory().addItem();
	}
	
	
	

}
