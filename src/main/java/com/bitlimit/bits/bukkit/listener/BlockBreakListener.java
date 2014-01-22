package com.bitlimit.bits.bukkit.listener;

import com.bitlimit.bits.persistence.PersistenceRunnable;
import com.bitlimit.bits.persistence.PersistentStoreCoordinator;
import com.bitlimit.bits.persistence.model.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockBreakEvent(final BlockBreakEvent blockBreakEvent)
	{
		this.onEvent(blockBreakEvent);

		PersistentStoreCoordinator.getSharedCoordinator().executePersistenceRunnable(new PersistenceRunnable()
		{
			@Override
			public void run()
			{
				Player.getPlayerForBukkitPlayer(blockBreakEvent.getPlayer()).incrementBlockBreakStatistic();
			}
		});
	}

	public static Integer getBitValuation()
	{
		return 1;
	}
}
