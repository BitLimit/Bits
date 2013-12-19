package com.bitlimit.bits.bukkit.listener;

import com.bitlimit.bits.persistence.PersistenceRunnable;
import com.bitlimit.bits.persistence.PersistentStoreCoordinator;
import com.bitlimit.bits.persistence.RewardTransaction;
import org.bukkit.Bukkit;
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
	public void onBlockBreakEvent(BlockBreakEvent blockBreakEvent)
	{
		this.onEvent(blockBreakEvent);

		PersistentStoreCoordinator.getSharedCoordinator().executePersistenceRunnable(new PersistenceRunnable()
		{
			@Override
			public void run()
			{
				RewardTransaction rewardTransaction = RewardTransaction.create();
				rewardTransaction.set("name", "test");

				try
				{
					rewardTransaction.saveIt();
				}
				catch (Exception e)
				{
					System.out.println("EXCEPTION: " + e.toString());
				}
				finally {

				}
			}
		});
	}
}
