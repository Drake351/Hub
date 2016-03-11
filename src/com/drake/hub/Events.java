package com.drake.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener{
	
	/*Donne les items lors de la connection*/
	@EventHandler
	public void giveOnJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.getInventory().setItem(0, Items.boussole());
		p.getInventory().setItem(4, Items.cosmetiques());
		p.getInventory().setItem(8, Items.joueursOff());
	}
	/*Interaction avec la boussole (jeux)*/
	@EventHandler
	public void onPlayerInteractJeux(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
	        if(p.hasPermission("hub.open.chests")){
			//if(p.getGameMode() != GameMode.CREATIVE){
				//if(p.isOp() == true){
	        	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
					if(p.getItemInHand().getType() == Material.COMPASS) {
				    	p.openInventory(KitsInventory.jeux);
				    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
					}
				}
	        }

		}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(p.hasPermission("hub.open.chests")){
				//if(p.getGameMode() != GameMode.CREATIVE){
					//if(p.isOp() == true){
		        	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
						if(p.getItemInHand().getType() == Material.COMPASS) {
					    	p.openInventory(KitsInventory.jeux);
					    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
						}
					}
		        }
		}
		
	}
	/*Interaction avec le firework(cosmétiques)*/
	@EventHandler
	public void onPlayerInteractCosmetique(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
	        if(p.hasPermission("hub.open.chests")){
			//if(p.getGameMode() != GameMode.CREATIVE){
			//if(p.isOp() == true){
		    	if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
		    		if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
		    			if(p.getItemInHand().getType() == Material.FIREWORK) {
		    				e.setCancelled(true);
		    				p.openInventory(KitsInventory.cosmetiques);
		    				p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
		    			}
		    		}
		    	}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
		    			if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
		    				if(p.getItemInHand().getType() == Material.FIREWORK) {
		    					e.setCancelled(true);
		    					p.openInventory(KitsInventory.cosmetiques);
		    					p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
		    				}
		       	
		    			}else{
		    				e.setCancelled(false);
		    			}
		    				
		    		}
		    	}
	        }
	/*Interaction joueursOn/Off*/
	@EventHandler
	public void onPlayerInteractJoueurs(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if(p.hasPermission("hub.open.chests")){
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Masque les joueurs")){
					e.setCancelled(true);
					p.sendMessage(ChatUtils.prefixHub()+"Tous les joueurs viennent de disparaître !");
					p.getInventory().setItemInHand(Items.joueursOn());
					p.playSound(loc,Sound.ORB_PICKUP, 4L, 2L);
					for(Player pls : Bukkit.getOnlinePlayers()){
						p.hidePlayer(pls);
					}
				}else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Affiche les joueurs")){
					e.setCancelled(true);
					p.sendMessage(ChatUtils.prefixHub() + "Les joueurs sont réapparus !");
					p.getInventory().setItemInHand(Items.joueursOff());
					p.playSound(loc,Sound.ORB_PICKUP, 4L, 2L);
					for(Player pls : Bukkit.getOnlinePlayers()){
						p.showPlayer(pls);
					}
				}
			}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Masque les joueurs")){
					e.setCancelled(true);
					p.sendMessage(ChatUtils.prefixHub()+"Tous les joueurs viennent de disparaître !");
					p.getInventory().setItemInHand(Items.joueursOn());
					p.playSound(loc,Sound.ORB_PICKUP, 4L, 2L);
					for(Player pls : Bukkit.getOnlinePlayers()){
						p.hidePlayer(pls);
					}
				}else if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Affiche les joueurs")){
					e.setCancelled(true);
					p.sendMessage(ChatUtils.prefixHub() + "Les joueurs sont réapparus !");
					p.getInventory().setItemInHand(Items.joueursOff());
					p.playSound(loc,Sound.ORB_PICKUP, 4L, 2L);
					for(Player pls : Bukkit.getOnlinePlayers()){
						p.showPlayer(pls);
					}
				}else{
    				e.setCancelled(false);
    			}
				
			
			}
		}
	}
	/*Event cancelled while trying to move anything in the inventory*/
	@EventHandler
	public void onInventoryVip(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked(); 
		ItemStack clicked = e.getCurrentItem(); 
		Inventory inventory = e.getInventory(); 
		//if (inventory.getType().equals(InventoryType.PLAYER)) {
			if (clicked.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){ 
				e.setCancelled(true);
			}else if(clicked.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
				e.setCancelled(true);
			}else if(clicked.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Masque les joueurs")){
				e.setCancelled(true);
			}else if(clicked.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Affiche les joueurs")){
				e.setCancelled(true);
			}
	
		
	}
}
