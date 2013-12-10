package com.bitlimit.bits.persistence;

import com.bitlimit.bits.configuration.ConfigurationManager;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;

public class PersistentStoreCoordinator
{
	/*
	 *
	 *  Singleton
	 *
	 */

	private static PersistentStoreCoordinator sharedCoordinator = null;

	protected PersistentStoreCoordinator()
	{
		this.plugin = Bukkit.getPluginManager().getPlugin("Bits");

		try
		{
			Pulse.recordCondition(this.plugin, "connecting to database.", Level.FINEST);

			Connection connection = DriverManager.getConnection(ConfigurationManager.getSharedManager().getPostgresURI(), ConfigurationManager.getSharedManager().getPostgresUsername(), ConfigurationManager.getSharedManager().getPostgresPassword());
			connection.close();

			Pulse.recordCondition(this.plugin, "connected to database.", Level.FINE);
		}
		catch (Exception e)
		{
			Pulse.recordCondition(this.plugin, "failed to connect to database.", Level.SEVERE);
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

	/*
	 *
	 *  Declarations
	 *
	 */

	private final Plugin plugin;

	/*
	 *
	 *  Implementation
	 *
	 */
}
