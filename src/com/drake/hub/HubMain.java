package com.drake.hub;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HubMain extends JavaPlugin {
	
	protected JavaPlugin plugin;
	/*Démarrage Plugin*/
	public void onEnable(){
		EventManager.RegisterEvent(this);
		this.getConfig().options().copyDefaults(true);
        saveConfig();
	}
	/*Commandes*/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    final Player p = (Player) sender;
	    /*Définir le lobby*/
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
		/*Téléporte au lobby*/
		}else if(cmd.getName().equalsIgnoreCase("lobby")){
			p.teleport((Location) this.getConfig().get("lobby"));
			p.sendMessage(ChatUtils.prefixHub()+ ChatColor.GRAY + "Téléportation vers le lobby");
		}
		return false;
	}
	
	
	

}
