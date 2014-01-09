package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import rx.Observable;
import rx.util.functions.Action1;

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
		return this.parent(Server.class);
	}

	/*
	 *
	 *  Demand Proxies
	 *
	 */

	public DemandLevel getDemandLevelForType(String type)
	{
		LazyList<DemandLevel> fetched = this.get(DemandLevel.class, "type = ?", type);
		DemandLevel demandLevel = null;

		if (fetched.size() > 0)
		{
			demandLevel = fetched.get(0);
		}

		if (demandLevel == null)
		{
			demandLevel = new DemandLevel();
			demandLevel.set("type", type);
			demandLevel.set("demand", 1F);
			demandLevel.setParent(this);
			demandLevel.saveIt();
		}

		return demandLevel;
	}

	public void degradeDemandLevelsWithBaseInterval(Integer baseInterval)
	{
		LazyList<DemandLevel> demandLevels = this.getAll(DemandLevel.class);
		Observable.from(demandLevels).subscribe(new Action1<DemandLevel>()
		{
			public void call(DemandLevel demandLevel)
			{

			}
		});
	}
}
