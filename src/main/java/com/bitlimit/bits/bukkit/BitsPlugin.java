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
		Integer port = 5432;
		String username = "yolo";
		String password = "swag";

		try
		{
			Pulse.recordCondition(this, "connecting to database.", Level.FINEST);

			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:" + port.toString() + "/bits", username, password);
			connection.close();

			Pulse.recordCondition(this, "connected to database.", Level.FINE);
		}
		catch (Exception e)
		{
			Pulse.recordCondition(this, "failed to connect to database.", Level.SEVERE);
		}
	}

}
