package com.bitlimit.bits.bukkit.listener;

import java.util.HashMap;

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

	private void loadFromConfig()
	{

	}
}
