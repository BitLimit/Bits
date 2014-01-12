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
		this.getCommand("bits").setExecutor(new CommandDispatch());

		Pulse.notePluginCondition(this, PluginCondition.LOADED, true, null);
	}

	@Override
	public void onDisable()
	{

	}

	public static BitsPlugin getPlugin()
	{
		return (BitsPlugin)Bukkit.getPluginManager().getPlugin("Bits");
	}

	public static String getBitsSymbol()
	{
		return "\u2756";
	}
}
