package com.bitlimit.bits.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ConfigurationManager
{
	private static ConfigurationManager sharedManager = null;

	private final Plugin plugin;
	private final FileConfiguration fileConfiguration;

	/*
	 *
	 *
	 * Singleton
	 *
	 *
	 */

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
	 *
	 * Base config
	 *
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
