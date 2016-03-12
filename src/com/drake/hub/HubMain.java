package com.drake.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HubMain extends JavaPlugin implements Listener {
	
	protected JavaPlugin plugin;
	/*Démarrage Plugin*/
	public void onEnable(){
		EventManager.RegisterEvent(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
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
					World world = p.getWorld();
					String path2 = "lobby-world";
					this.getConfig().set(path, hub);
					this.getConfig().set(path2, world);
					this.saveConfig();
					p.sendMessage(ChatUtils.prefixHub()+ "Lobby défini !");
				}
			}else{
				System.out.println("Tu dois être joueur pour faire cette commande");
			}
		/*Téléporte au lobby*/
		}else if(cmd.getName().equalsIgnoreCase("lobby")){
			if(sender instanceof Player){
				p.teleport((Location) this.getConfig().get("lobby"));
				p.sendMessage(ChatUtils.prefixHub()+ ChatColor.GRAY + "Téléportation vers le lobby");
			}else{
				System.out.println("Tu dois être joueur pour faire cette commande");
			}
		}
		return false;
	}
	/*Clear des drops dans le monde du lobby*/
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		if(p.getLocation().getWorld().equals(this.getConfig().get("lobby-world"))){
			e.getDrops().clear();
		}
	}
}
