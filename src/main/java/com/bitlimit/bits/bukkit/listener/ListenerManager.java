package com.bitlimit.bits.bukkit.listener;

import com.bitlimit.bits.configuration.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import rx.Observable;
import rx.util.functions.Action1;
import rx.util.functions.Func1;
import rx.util.functions.Func2;


import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/10/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListenerManager
{
	/*
	 *
	 *  Singleton
	 *
	 */

	private static ListenerManager sharedInstance = new ListenerManager();

	public static ListenerManager getListenerManager()
	{
		return sharedInstance;
	}

	/*
	 *
	 *  Implementation
	 *
	 */

	private final HashMap<Class, EventListener> eventListeners;
	private ListenerManager()
	{
		this.eventListeners = new HashMap<Class, EventListener>();

		this.loadFromConfig();
	}

	public void initialize()
	{

	}

	private void loadFromConfig()
	{
		final HashMap<Class, EventListener> eventListenerHashMap = this.eventListeners;

		ConfigurationManager.getSharedManager().getMonitoredEventClasses().subscribe(new Action1()
		{
			public void call(Object o)
			{
				Class<Event> eventClass = (Class<Event>)o;
				eventListenerHashMap.put(eventClass, ListenerManager.listenerForEventClass(eventClass));
			}
		});
	}

	private static EventListener listenerForEventClass(Class<Event> eventClass)
	{
		if (eventClass == null)
		{
			return null;
		}

		if (eventClass.equals(BlockBreakEvent.class))
		{
			return new BlockBreakListener();
		}
		else if (eventClass.equals(BlockPlaceEvent.class))
		{
			return new BlockPlaceListener();
		}

		return null;
	}
}
