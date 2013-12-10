package com.bitlimit.bits.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigurationManager
{
	/*
	 *
	 *  Singleton
	 *
	 */

	private static ConfigurationManager sharedManager = null;

	protected ConfigurationManager()
	{
		this.plugin = Bukkit.getPluginManager().getPlugin("Bits");

		this.fileConfiguration = this.plugin.getConfig();
		this.plugin.getConfig().options().copyDefaults(true);
	}

	public static ConfigurationManager getSharedManager()
	{
		if (sharedManager == null)
		{
			sharedManager = new ConfigurationManager();
		}

		return sharedManager;
	}

	/*
	 *
	 *  Declarations
	 *
	 */

	private final Plugin plugin;
	private final FileConfiguration fileConfiguration;

	/*
	 *
	 *  Implementation
	 *
	 */

	public void saveConfig()
	{
		this.plugin.saveConfig();
	}

	/*
	 *
	 *
	 * Persistence
	 *
	 *
	 */

	private ConfigurationSection getPersistenceSection()
	{
		return this.fileConfiguration.getConfigurationSection("persistence");
	}

	public String getPostgresURI()
	{
		return this.getPersistenceSection().getString("uri");
	}

	public String getPostgresUsername()
	{
		return this.getPersistenceSection().getString("username");
	}

	public String getPostgresPassword()
	{
		return this.getPersistenceSection().getString("password");
	}
}
