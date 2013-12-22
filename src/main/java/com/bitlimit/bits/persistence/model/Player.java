package com.bitlimit.bits.persistence.model;

import org.bukkit.Bukkit;
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
	/*
	 *
	 *  Designated Constructor
	 *
	 */

	public static Player getPlayerForBukkitPlayer(final OfflinePlayer offlinePlayer)
	{
		String playerName = offlinePlayer.getName();
		Player player = Player.findFirst("name = ?", playerName);

		Server.getCurrentServer();

		if (player == null)
		{
			player = new Player();
			player.set("name", playerName);

			player.insert();

			PlayerServerRecord playerServerRecord = new PlayerServerRecord();
			playerServerRecord.insert();

			player.add(playerServerRecord);
			player.save();
		}

		return player;
	}

	/*
	 *
	 *  Statistic Proxies
	 *
	 */

	public PlayerServerRecord getPlayerServerRecord()
	{
		return this.getAll(PlayerServerRecord.class).get(0);
	}

	public Integer incrementBlockBreakStatistic()
	{
		PlayerServerRecord playerServerRecord = this.getPlayerServerRecord();
		PlayerStatistic blockBreakStatistic = playerServerRecord.getPlayerStatisticWithType("block-break");

		Integer newValue = blockBreakStatistic.getInteger("block-break") + 1;
		blockBreakStatistic.set("block-break", newValue);

		return newValue;
	}


}
