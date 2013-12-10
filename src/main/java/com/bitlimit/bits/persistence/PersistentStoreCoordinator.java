package com.bitlimit.bits.persistence;

import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

public class PersistentStoreCoordinator
{
	private static PersistentStoreCoordinator sharedCoordinator = null;

	private final Plugin plugin;

	protected PersistentStoreCoordinator()
	{
		this.plugin = Bukkit.getPluginManager().getPlugin("Bits");

		try
		{
			Pulse.recordCondition(this.plugin, "connecting to database.", Level.FINEST);

			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:" + port.toString() + "/bits", username, password);
			connection.close();

			Pulse.recordCondition(this, "connected to database.", Level.FINE);
		}
		catch (Exception e)
		{
			Pulse.recordCondition(this, "failed to connect to database.", Level.SEVERE);
		}
	}

	public static PersistentStoreCoordinator getSharedCoordinator()
	{
		if (sharedCoordinator == null)
		{
			sharedCoordinator = new PersistentStoreCoordinator();
		}

		return sharedCoordinator;
	}
}
