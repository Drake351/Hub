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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HubMain extends JavaPlugin implements Listener {
	
	protected JavaPlugin plugin;
	/*D�marrage Plugin*/
	public void onEnable(){
		EventManager.RegisterEvent(this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
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
					p.sendMessage(ChatUtils.prefixAdmin()+ "Lobby d�fini !");
				}
			}else{
				System.out.println("Tu dois �tre joueur pour faire cette commande");
			}
		/*T�l�porte au lobby*/
		}else if(cmd.getName().equalsIgnoreCase("lobby")){
			if(sender instanceof Player){
				p.teleport((Location) this.getConfig().get("lobby"));
				p.sendMessage(ChatUtils.prefixHub()+ ChatColor.GRAY + "T�l�portation vers le lobby");
			}else{
				System.out.println("Tu dois �tre joueur pour faire cette commande");
			}
		}else if(cmd.getName().equalsIgnoreCase("setmotd")){
			if(sender instanceof Player){
				if(args.length >= 1){
					if(!p.hasPermission("hub.set.motd")){p.sendMessage("Tu n'as pas la permission");}
					if(p.hasPermission("hub.set.motd")){
						StringBuilder str = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                                str.append(args[i] + " ");
                        }
                        String motd = str.toString();
                        getConfig().set("motd", motd);
                        saveConfig();
						p.sendMessage(ChatUtils.prefixAdmin() + "Motd d�fini en " + motd);
					}
					
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void playerJoinMotd(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String string = (String) getConfig().get("motd");
		String string2 = string.replaceAll("&" , "�");
		String string3 = string2.replaceAll("��" , "&");
		String message = string3.replaceAll("%p", p.getCustomName());
		p.sendMessage(message);
	}
}
