package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/21/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

@BelongsToParents({
		                  @BelongsTo(parent = Server.class, foreignKeyName = "id_servers"),
		                  @BelongsTo(parent = Player.class, foreignKeyName = "id_players")
})

public class PlayerServerRecord extends Model
{
	/*
	 *
	 *  Statistics Proxies
	 *
	 */

	public PlayerStatistic getPlayerStatisticWithType(String type)
	{
		LazyList<PlayerStatistic> fetched = this.get(PlayerStatistic.class, "type = ?", type);
		PlayerStatistic playerStatistic = null;

		if (fetched.size() > 0)
		{
			playerStatistic = fetched.get(0);
		}

		if (playerStatistic == null)
		{
			playerStatistic = new PlayerStatistic();
			playerStatistic.set("type", type);
			playerStatistic.set("value", 0);
			playerStatistic.setParent(this);
			playerStatistic.saveIt();
		}

		return playerStatistic;
	}

	/*
	 *
	 *  Convenience
	 *
	 */

	public Player getPlayer()
	{
		return this.parent(Player.class);
	}

	public Server getServer()
	{
		return this.parent(Server.class);
	}
}
