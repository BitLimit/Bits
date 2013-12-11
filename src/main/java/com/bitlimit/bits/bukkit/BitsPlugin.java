package com.bitlimit.bits.bukkit;

import com.bitlimit.pulse.PluginCondition;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.bitlimit.pulse.Pulse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;


public class BitsPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		Pulse.notePluginCondition(this, PluginCondition.LOADED, true, null);
	}

	@Override
	public void onDisable()
	{

	}

	public static BitsPlugin getPlugin()
	{
		return (BitsPlugin)Bukkit.getPluginManager().getPlugin("Bits");
	}
}