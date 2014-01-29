package com.bitlimit.bits.persistence;

import com.bitlimit.bits.bukkit.BitsPlugin;
import com.bitlimit.bits.configuration.ConfigurationManager;
import com.bitlimit.bits.persistence.model.Market;
import com.bitlimit.bits.persistence.model.Server;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.*;
import java.util.logging.Level;

public class PersistentStoreCoordinator implements Listener
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

	private MarketManager marketManager;

	/*
	 *
	 *  Implementation
	 *
	 */

	protected PersistentStoreCoordinator()
	{
		this.plugin = BitsPlugin.getPlugin();
		Bukkit.getPluginManager().registerEvents(this, this.plugin);

		this.executorService = Executors.newFixedThreadPool(4, new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				return new PersistenceThread(runnable);
			}
		});

		this.executePersistenceRunnable(new PersistenceRunnable()
		{
			public void run()
			{
				marketManager = new MarketManager(Server.getCurrentServer().getMarket());
			}
		});
	}

	@EventHandler (priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPluginDisableEvent(PluginDisableEvent pluginDisableEvent)
	{
		if (!this.executorService.isShutdown())
		{
			this.executorService.shutdown();
		}
	}

	public Future<?> executePersistenceRunnable(PersistenceRunnable runnable)
	{
		return this.executorService.submit(runnable);
	}
}
