package com.drake.hub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	
	public static ItemStack boussole(){
		 ItemStack item= new ItemStack(Material.COMPASS, 1);
	     ItemMeta meta = item.getItemMeta();
	     meta.setDisplayName(ChatColor.DARK_RED+"Jeux");
	     List<String> lore = new ArrayList<String>(); 
	     lore.add(ChatColor.GRAY+"Affiche la liste des jeux");
	     meta.setLore(lore);
	     item.setItemMeta(meta);
	     return item;

	}
	
	public static ItemStack cosmetiques(){
		 ItemStack item= new ItemStack(Material.FIREWORK, 1);
	     ItemMeta meta = item.getItemMeta();
	     meta.setDisplayName(ChatColor.DARK_PURPLE+"Cosmétiques");
	     List<String> lore = new ArrayList<String>(); 
	     lore.add(ChatColor.GRAY+"Ouvre la liste des cosmétiques");
	     meta.setLore(lore);
	     item.setItemMeta(meta);
	     return item;

	}

	public static ItemStack clock() {
		 ItemStack item= new ItemStack(Material.WATCH, 1);
	     ItemMeta meta = item.getItemMeta();
	     meta.setDisplayName(ChatColor.GREEN+"Masque/Affiche les joueurs");
	     List<String> lore = new ArrayList<String>(); 
	     lore.add(ChatColor.GRAY+"Masque/Affiche les joueurs");
	     meta.setLore(lore);
	     item.setItemMeta(meta);
	     return item;
	}
}
