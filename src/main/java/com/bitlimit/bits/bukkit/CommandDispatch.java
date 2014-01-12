package com.bitlimit.bits.bukkit;

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
			Player player = (Player)commandSender;

			player.sendMessage(ChatColor.GOLD + "You have " + BitsPlugin.getBitsSymbol() + com.bitlimit.bits.persistence.model.Player.getPlayerForBukkitPlayer(player).getBits() + ".");
		}

		return false;
	}
}
