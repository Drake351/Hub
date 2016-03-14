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
					this.getConfig().set(path, hub);
					this.saveConfig();
					p.sendMessage(ChatUtils.prefixAdmin()+ "Lobby défini !");
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
						p.sendMessage(ChatUtils.prefixAdmin() + "Motd défini en " + motd);
					}
					
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("setOpMotd")){
			if(sender instanceof Player){
				if(args.length >= 1){
					if(!p.hasPermission("hub.set.motdop")){p.sendMessage("Tu n'as pas la permission");}
					if(p.hasPermission("hub.set.motdop")){
						StringBuilder str = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                                str.append(args[i] + " ");
                        }
                        String op = str.toString();
                        getConfig().set("motdOp", op);
                        saveConfig();
						p.sendMessage(ChatUtils.prefixAdmin() + "Motd pour Op défini en " + op);
					}
					
				}
			}
		
		}
		return false;
	}
	
	@EventHandler
	public void playerJoinMotd(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String playerName = p.getName();
		if(!p.isOp()){
			String string = (String) getConfig().get("motd");
			String string2 = string.replaceAll("&" , "§");
			String string3 = string2.replaceAll("§§" , "&");
			String message = string3.replaceAll("%p", playerName);
			p.sendMessage(message);
		}else if(p.isOp()){
			String op = (String) this.getConfig().get("motdOp");
			String op1 = op.replaceAll("&" , "§");
			String op2 = op1.replaceAll("§§" , "&");
			String  op3 = op2.replaceAll("%p", playerName);
			String op4 = op3.replaceAll("%listp", Bukkit.getOnlinePlayers().toString());
			String op5 = op4.replaceAll("%n", "\n");
			p.sendMessage(op5);
		}
	}
}
