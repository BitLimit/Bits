package com.bitlimit.bits.bukkit.listener;

import com.bitlimit.bits.bukkit.BitsPlugin;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/10/13
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventListener implements Listener
{
	public EventListener()
	{
		Bukkit.getPluginManager().registerEvents(this, BitsPlugin.getPlugin());

		System.out.println("registered events with plugin: " + BitsPlugin.getPlugin().toString());
	}

	public void onEvent(Event event)
	{
		System.out.println("sup");
		Pulse.recordCondition(BitsPlugin.getPlugin(), event.getEventName() + " received.", Level.FINEST);
	}
}
