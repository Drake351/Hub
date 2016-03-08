package com.drake.me;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {
	final public static void RegisterEvent(Plugin pl){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Events(), pl);
	}
}
