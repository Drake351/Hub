package com.drake.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
	/*Give back items to the player while respawn*/
	@EventHandler
	public void PlayerRespawn(PlayerRespawnEvent e){
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
	        	if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
					if(p.getInventory().getItemInHand().getType() == Material.COMPASS) {
				    	p.openInventory(ChestsInventory.jeux);
				    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
					}
				}
	        }

		}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(p.hasPermission("hub.open.chests")){
				//if(p.getGameMode() != GameMode.CREATIVE){
					//if(p.isOp() == true){
		        	if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
						if(p.getInventory().getItemInHand().getType() == Material.COMPASS) {
					    	p.openInventory(ChestsInventory.jeux);
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
	        if(p.getItemInHand() != null){
		    	if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
		    		if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
		    			if(p.getInventory().getItemInHand().getType() == Material.FIREWORK) {
		    				e.setCancelled(true);
		    				p.openInventory(ChestsInventory.cosmetiques);
		    				p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
		    			}
		    		}
		    	}else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
		    		if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
		    			if(p.getInventory().getItemInHand().getType() == Material.FIREWORK) {
		    				p.openInventory(ChestsInventory.cosmetiques);
		    				p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
		    				e.setCancelled(true);
		    			}
		    		}	
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
			if(p.getInventory().getItemInHand() != null){
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
					}
				}
			}
		}
	}
	/*Event cancelled while trying to move anything in the inventory*/
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		ItemStack clicked = e.getCurrentItem(); 
		//if (inventory.getType().equals(InventoryType.PLAYER)) {
		if(clicked != null){
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
	
	/*Event cancelled while trying to drop anything from the inventory*/
	@EventHandler
	public void ItemDropEvent(PlayerDropItemEvent e){
		Item item = e.getItemDrop();
		if(item!= null){
			if(item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
				e.setCancelled(true);
			}else if(item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
				e.setCancelled(true);
			}else if(item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Affiche les joueurs")){
				e.setCancelled(true);
			}else if(item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Masque les joueurs")){
				e.setCancelled(true);
			}
		}
	}
}
