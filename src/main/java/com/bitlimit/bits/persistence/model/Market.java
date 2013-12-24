package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/21/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */

@BelongsTo(parent = Server.class, foreignKeyName = "id_servers")
public class Market extends Model
{
	/*
	 *
	 *  Getters
	 *
	 */

	public Server getServer()
	{
		return this.getAll(Server.class).get(0);
	}

	/*
	 *
	 *  Demand Proxies
	 *
	 */

	public DemandLevel getDemandLevelForType(String type)
	{
		DemandLevel demandLevel = this.get(DemandLevel.class, "type = ?", type).get(0);

		if (demandLevel == null)
		{
			demandLevel = new DemandLevel();
			demandLevel.set("type", type);
			demandLevel.set("demand", 1F);
			demandLevel.setParents(this);
			demandLevel.saveIt();
		}

		return demandLevel;
	}
}
