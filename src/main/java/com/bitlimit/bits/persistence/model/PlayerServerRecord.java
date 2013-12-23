package com.bitlimit.bits.persistence.model;

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

@BelongsToParents({@BelongsTo(parent = Server.class, foreignKeyName = "id_servers"), @BelongsTo(parent = Player.class, foreignKeyName = "id_players")})
public class PlayerServerRecord extends Model
{
	public PlayerStatistic getPlayerStatisticWithType(String type)
	{
		PlayerStatistic playerStatistic = this.get(PlayerStatistic.class, "type = ?", type).get(0);

		if (playerStatistic == null)
		{
			playerStatistic = new PlayerStatistic();
			playerStatistic.set("type", type);
			playerStatistic.insert();

			this.add(playerStatistic);
		}

		return playerStatistic;
	}
}
