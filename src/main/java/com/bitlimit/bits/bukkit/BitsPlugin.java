package com.bitlimit.bits.bukkit;

import com.bitlimit.pulse.PluginCondition;
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

		this.initializeConnection();
	}

	@Override
	public void onDisable()
	{

	}

	public void initializeConnection()
	{

	}

}
