package com.bitlimit.bits.persistence.model;

import org.bukkit.OfflinePlayer;
import org.javalite.activejdbc.Model;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/21/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Model
{
	public static Player playerForBukkitPlayer(OfflinePlayer offlinePlayer)
	{
		String playerName = offlinePlayer.getName();
		Player player = Player.findFirst("name = ?", playerName);

		if (player == null)
		{
			player = new Player();
			player.set("name", playerName);
			player.insert();
		}

		return player;
	}
}
