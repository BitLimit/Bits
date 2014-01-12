package com.bitlimit.bits.bukkit;

import com.bitlimit.bits.persistence.PersistenceRunnable;
import com.bitlimit.bits.persistence.PersistentStoreCoordinator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 1/12/14
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandDispatch implements CommandExecutor
{
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
	{
		if (commandSender instanceof Player)
		{
			final Player player = (Player)commandSender;

			PersistentStoreCoordinator.getSharedCoordinator().executePersistenceRunnable(new PersistenceRunnable()
			{
				public void run()
				{
					final Float bitsCount = com.bitlimit.bits.persistence.model.Player.getPlayerForBukkitPlayer(player).getBits();

					Bukkit.getScheduler().scheduleSyncDelayedTask(BitsPlugin.getPlugin(), new Runnable()
					{
						public void run()
						{
							player.sendMessage(ChatColor.GOLD + "You have " + BitsPlugin.getBitsSymbol() + bitsCount + ".");
						}
					});
				}
			});
		}

		return false;
	}
}
