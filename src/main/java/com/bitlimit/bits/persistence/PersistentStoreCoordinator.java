package com.bitlimit.bits.persistence;

import com.bitlimit.bits.configuration.ConfigurationManager;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

	private final ThreadPoolExecutor threadPoolExecutor;
	private final ArrayBlockingQueue<Runnable> blockingQueue;

	/*
	 *
	 *  Implementation
	 *
	 */

	protected PersistentStoreCoordinator()
	{
		this.plugin = Bukkit.getPluginManager().getPlugin("Bits");

		this.blockingQueue = new ArrayBlockingQueue<Runnable>(2048);
		this.threadPoolExecutor = new ThreadPoolExecutor(4, 4, 16, TimeUnit.SECONDS, this.blockingQueue);
		this.threadPoolExecutor.setThreadFactory(new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				return new PersistenceThread(runnable);
			}
		});

	}

	public void executePersistenceRunnable(PersistenceRunnable runnable)
	{
		this.threadPoolExecutor.execute(runnable);
	}
}
