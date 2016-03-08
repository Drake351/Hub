package com.drake.me;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class HubMain extends JavaPlugin {
	/*Démarrage Plugin*/
	public static HubMain plugin;
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
			if(!p.hasPermission("hub.set.lobby")){p.sendMessage("Tu n'as pas la permission");}
			if(p.hasPermission("hub.set.lobby")){
				Location hub = p.getLocation();
				String path = "lobby";
				this.getConfig().set(path, hub);
    			this.saveConfig();
			}
		/*Téléporte au lobby*/
		}else if(cmd.getName().equalsIgnoreCase("lobby")){
			p.teleport((Location) this.getConfig().get("lobby"));
			p.sendMessage(ChatUtils.getGamePrefix()+ ChatColor.GRAY + "Téléportation vers le lobby");
		}
		return false;
	}
	
	
	

}
