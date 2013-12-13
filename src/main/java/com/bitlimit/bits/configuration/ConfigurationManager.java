package com.bitlimit.bits.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

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

	/*
	 *
	 *  Gameplay Mechanics
	 *
	 */

	private ConfigurationSection getGameplayMechanicsSection()
	{
		return this.fileConfiguration.getConfigurationSection("gameplay-mechanics");
	}

	/*
	 *
	 *  Listeners
	 *
	 */

	private static HashMap<String, Class> getEventClassAssociations()
	{
		HashMap<String, Class> classHashMap = new HashMap<String, Class>();
		classHashMap.put("block-break", BlockBreakEvent.class);
		classHashMap.put("block-place", BlockPlaceEvent.class);

//		classHashMap.put("entity-leash", PlayerLeashEntityEvent.class);
		classHashMap.put("entity-kill", EntityDeathEvent.class);
		classHashMap.put("entity-shear", PlayerShearEntityEvent.class);

		classHashMap.put("item-brew", BrewEvent.class);
		classHashMap.put("item-enchant", EnchantItemEvent.class);
		classHashMap.put("item-furnace", FurnaceExtractEvent.class);

		classHashMap.put("painting-create", HangingPlaceEvent.class);

		classHashMap.put("player-regenerate", EntityRegainHealthEvent.class);
		classHashMap.put("player-shoot", EntityShootBowEvent.class);
		classHashMap.put("player-chat", AsyncPlayerChatEvent.class);
		classHashMap.put("player-death", PlayerDeathEvent.class);
		classHashMap.put("player-sleep", PlayerBedEnterEvent.class);
		classHashMap.put("player-author", PlayerEditBookEvent.class);
		classHashMap.put("player-egg", PlayerEggThrowEvent.class);
		classHashMap.put("player-fish", PlayerFishEvent.class);
		classHashMap.put("player-eat", PlayerItemConsumeEvent.class);
		classHashMap.put("player-join", PlayerJoinEvent.class);
		classHashMap.put("player-move", PlayerMoveEvent.class);

		classHashMap.put("portal-create", PortalCreateEvent.class);

		classHashMap.put("vehicle-move", VehicleMoveEvent.class);

		classHashMap.put("world-change", PlayerChangedWorldEvent.class);

		return classHashMap;
	}

	public List<Class<Event>> getMonitoredEventClasses()
	{
		List<String> toMonitorList = this.getGameplayMechanicsSection().getStringList("monitor");
		HashMap<String, Class> classMap = ConfigurationManager.getEventClassAssociations();

		ArrayList<Class<Event>> eventClasses = new ArrayList<Class<Event>>();

		ListIterator monitorIterator = toMonitorList.listIterator();

		while (monitorIterator.hasNext())
		{
			String monitor = (String)monitorIterator.next();
			eventClasses.add(classMap.get(monitor));
		}

		return eventClasses;
	}
}
