package com.bitlimit.bits.persistence.model;

import com.bitlimit.bits.bukkit.BitsPlugin;
import com.bitlimit.bits.bukkit.listener.BlockBreakListener;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.javalite.activejdbc.Model;

import java.util.logging.Level;

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

		if (player == null)
		{
			player = new Player();
			player.setName(playerName);
			player.saveIt();

			Pulse.recordCondition(BitsPlugin.getPlugin(), "Created player model for " + playerName, Level.FINER);

			PlayerServerRecord playerServerRecord = new PlayerServerRecord();
			playerServerRecord.saveIt();

			Pulse.recordCondition(BitsPlugin.getPlugin(), "Created player server record for " + playerName, Level.FINER);

			Server server = Server.getCurrentServer();
			playerServerRecord.setParent(server);
			playerServerRecord.saveIt();

			player.add(playerServerRecord);
			player.saveIt();

			player.sendIntroduction();
		}

		return player;
	}

	/*
	 *
	 *  Getters
	 *
	 */

	public PlayerServerRecord getPlayerServerRecord()
	{
		return this.getAll(PlayerServerRecord.class).get(0);
	}

	public String getName()
	{
		return this.getString("name");
	}

	public Integer getBits()
	{
		return this.getInteger("bits");
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

	public void setBits(Integer bits)
	{
		this.setInteger("bits", bits);
	}

	public boolean adjustBitsByAmount(Integer amount)
	{
		Integer currentBalance = this.getBits();
		if (currentBalance == null)
		{
			currentBalance = 0;
		}

		Integer nextBalance = currentBalance + amount;

		if (nextBalance < 0)
		{
			return false;
		}

		this.setBits(nextBalance);

		return true;
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
				Bukkit.getPlayer(self.getName()).sendMessage(ChatColor.AQUA + "Welcome to Bits! You've been created an account that pays Bits (" + BitsPlugin.getBitsSymbol() + ") for every action you take in-game. Type \"/bits tutorial\" for a walkthrough.");
			}
		});
	}

	/*
	 *
	 *  Statistic Proxies
	 *
	 */

	public Integer incrementBlockBreakStatistic()
	{
		PlayerServerRecord playerServerRecord = this.getPlayerServerRecord();

		PlayerStatistic blockBreakStatistic = playerServerRecord.getPlayerStatisticWithType("block-break");

		Integer newValue = blockBreakStatistic.getInteger("value") + 1;
		blockBreakStatistic.set("value", newValue);
		blockBreakStatistic.saveIt();

		this.adjustBitsByAmount(BlockBreakListener.getBitValuation());
		this.saveIt();

<<<<<<< HEAD
		saturationLevel.adjustDemandByAmount(1F);
		saturationLevel.saveIt();

=======
>>>>>>> linear
		return newValue;
	}


}
