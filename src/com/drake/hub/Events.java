package com.drake.hub;

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
		p.getInventory().setItem(8, Items.clock());
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
	        	if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"Jeux")){
					if(e.getPlayer().getItemInHand().getType() == Material.COMPASS) {
				    	p.openInventory(KitsInventory.jeux);
				    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
					}
				}
	        }
		}
	}
	/*Interaction avec la clock(cosmétiques)*/
	@EventHandler
	public void onPlayerInteractCosmetique(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
	        if(p.hasPermission("hub.open.chests")){
			//if(p.getGameMode() != GameMode.CREATIVE){
				//if(p.isOp() == true){
	        	if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE+"Cosmétiques")){
					if(e.getPlayer().getItemInHand().getType() == Material.FIREWORK) {
				    	p.openInventory(KitsInventory.cosmetiques);
				    	p.playSound(loc,Sound.NOTE_PLING, 4L, 2L);
					}
				}
	        }
		}
	}
	
}
