package com.bitlimit.bits.bukkit.listener;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/10/13
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockBreakListener extends EventListener
{
	public void onBlockBreakEvent(BlockBreakEvent blockBreakEvent)
	{
		this.onEvent(blockBreakEvent);
	}
}
