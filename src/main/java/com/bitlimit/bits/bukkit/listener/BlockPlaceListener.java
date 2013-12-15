package com.bitlimit.bits.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/10/13
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockPlaceListener extends EventListener
{
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockPlaceEvent(BlockPlaceEvent blockPlaceEvent)
	{
		this.onEvent(blockPlaceEvent);
	}
}
