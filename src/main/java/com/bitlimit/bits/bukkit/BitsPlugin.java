package com.bitlimit.bits.bukkit;

import com.bitlimit.bits.bukkit.listener.ListenerManager;
import com.bitlimit.bits.persistence.PersistenceRunnable;
import com.bitlimit.bits.persistence.PersistentStoreCoordinator;
import com.bitlimit.bits.persistence.model.Server;
import com.bitlimit.pulse.PluginCondition;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.bitlimit.pulse.Pulse;

public class BitsPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		ListenerManager.getListenerManager().initialize();

		Pulse.notePluginCondition(this, PluginCondition.LOADED, true, null);

		PersistentStoreCoordinator.getSharedCoordinator().executePersistenceRunnable(new PersistenceRunnable()
		{
			@Override
			public void run()
			{
				Server server = Server
			}
		});
	}

	@Override
	public void onDisable()
	{

	}

	public static BitsPlugin getPlugin()
	{
		return (BitsPlugin)Bukkit.getPluginManager().getPlugin("Bits");
	}
}
