package com.drake.hub;

import org.bukkit.ChatColor;

public class ChatUtils {
	
	static String prefix = ChatColor.BLUE + "Hub" + ChatColor.BLUE +"> "+ ChatColor.GRAY;
	static String prefixAdmin = ChatColor.BLUE + "Admin" + ChatColor.BLUE + "> " + ChatColor.GRAY;
	
	public static String prefixHub(){
		return prefix;
	}

	public static String prefixAdmin() {
		return prefixAdmin;
	} 
}
