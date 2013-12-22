package com.bitlimit.bits.persistence.model;

import com.bitlimit.bits.bukkit.BitsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

			player.sendIntroduction();
		}

		return player;
	}

	/*
	 *
	 *  Getters
	 *
	 */

	public String getName()
	{
		return this.getString("name");
	}

	/*
	 *
	 *  Setters
	 *
	 */

	public void setName(String name)
	{
		this.setString("name", name);
	}

	/*
	 *
	 *  Player-Facing Side
	 *
	 */

	private void sendIntroduction()
	{
		final Player self = this;
		Bukkit.getScheduler().scheduleSyncDelayedTask(BitsPlugin.getPlugin(), new Runnable()
		{
			public void run()
			{
				Bukkit.getPlayer(self.getName()).sendMessage(ChatColor.AQUA + "Welcome to Bits! You've been created an account that pays Bits (" + BitsPlugin.getBitsSymbol() + ") for every action you take in-game.");
			}
		});
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
