package com.bitlimit.bits.persistence;

import com.bitlimit.bits.configuration.ConfigurationManager;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.*;
import java.util.logging.Level;

public class PersistentStoreCoordinator
{
	/*
	 *
	 *  Singleton
	 *
	 */

	private static PersistentStoreCoordinator sharedCoordinator = null;

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

	private final ExecutorService executorService;

	/*
	 *
	 *  Implementation
	 *
	 */

	protected PersistentStoreCoordinator()
	{
		this.plugin = Bukkit.getPluginManager().getPlugin("Bits");

		this.executorService = Executors.newFixedThreadPool(4, new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				return new PersistenceThread(runnable);
			}
		});
	}

	public void executePersistenceRunnable(PersistenceRunnable runnable)
	{
		this.executorService.submit(runnable);
	}
}
