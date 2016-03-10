package com.drake.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class KitsInventory {
	

	public static Inventory jeux = Bukkit.createInventory(null, 54,ChatColor.DARK_RED+"Liste des jeux !");
	static {
		jeux.setItem(0, new ItemStack(Material.DIRT));
		jeux.setItem(1, new ItemStack(Material.DIRT));
		jeux.setItem(2, new ItemStack(Material.DIRT));
		jeux.setItem(3, new ItemStack(Material.DIRT));
		jeux.setItem(4, new ItemStack(Material.DIRT));
		jeux.setItem(5, new ItemStack(Material.DIRT));
	}
	
	public static Inventory cosmetiques = Bukkit.createInventory(null, 54,ChatColor.DARK_PURPLE+"Liste des cosmétiques !");
	static {
		cosmetiques.setItem(0, new ItemStack(Material.DIRT));
		cosmetiques.setItem(1, new ItemStack(Material.DIRT));
		cosmetiques.setItem(2, new ItemStack(Material.DIRT));
		cosmetiques.setItem(3, new ItemStack(Material.DIRT));
		cosmetiques.setItem(4, new ItemStack(Material.DIRT));
		cosmetiques.setItem(5, new ItemStack(Material.DIRT));
	}
	
}