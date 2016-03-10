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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{

	/*Donne les items lors de la connection*/
	@EventHandler
	public void giveOnJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.getInventory().setItem(0, Items.boussole());
		p.getInventory().setItem(4, Items.cosmetiques());
		p.getInventory().setItem(8, Items.clockOff());
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
		}
	}
	/*Interaction avec le firework(cosm�tiques)*/
	@EventHandler
	public void onPlayerInteractCosmetique(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
	        if(p.hasPermission("hub.open.chests")){
			//if(p.getGameMode() != GameMode.CREATIVE){
			//if(p.isOp() == true){
	        	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosm�tiques")){
					if(p.getItemInHand().getType() == Material.FIREWORK) {
				    	p.openInventory(KitsInventory.cosmetiques);
				    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
					}
	        	}
	        }
	}
	
	/*Interaction avec la clock(joueurs masque....)*/
	@EventHandler
	public void onPlayerInteractWatchOff(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
	    if(p.hasPermission("hub.open.chests")){
		//if(p.getGameMode() != GameMode.CREATIVE){
		//if(p.isOp() == true){
	    	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Masque les joueurs")){
				//if(p.getItemInHand().getType() == Material.WATCH) {
			    	p.playSound(loc,Sound.PORTAL_TRAVEL, 4L, 2L);
			    	p.sendMessage(ChatUtils.prefixHub()+ "Tous les joueurs viennent de dispara�tre !");
			    	p.setItemInHand(Items.clockOn());
			    	for(Player pls : Bukkit.getOnlinePlayers()){
			    		if(pls != p){
			    			p.hidePlayer(pls);
			    		}
			    	}
			}
	    }
	}
	
	/*Interaction avec la clock(joueurs affiche..)*/
	@EventHandler
	public void onPlayerInteractWatchOn(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
	        if(p.hasPermission("hub.open.chests")){
			//if(p.getGameMode() != GameMode.CREATIVE){
			//if(p.isOp() == true){
	        	if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"Affiche les joueurs")){
	        		//if(p.getItemInHand().getType() == Material.WATCH) {
	        			p.playSound(loc,Sound.PORTAL_TRAVEL, 4L, 2L);
	        			p.sendMessage(ChatUtils.prefixHub()+"Les joueurs sont � nouveau visibles !");
	        			p.setItemInHand(Items.clockOff());
	        			for(Player pls : Bukkit.getOnlinePlayers()){
	        				if(pls != p){
	        					p.showPlayer(pls);
	        				}
	        			}
				    	
	        	}
	        }
	}
	
}
