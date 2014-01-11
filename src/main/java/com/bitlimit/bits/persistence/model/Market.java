package com.bitlimit.bits.persistence.model;

import org.bukkit.Bukkit;
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

	public SaturationLevel getDemandLevelForType(String type)
	{
		LazyList<SaturationLevel> fetched = this.get(SaturationLevel.class, "type = ?", type);
		SaturationLevel saturationLevel = null;

		if (fetched.size() > 0)
		{
			saturationLevel = fetched.get(0);
		}

		if (saturationLevel == null)
		{
			saturationLevel = new SaturationLevel();
			saturationLevel.set("type", type);
			saturationLevel.set("demand", 1F);
			saturationLevel.setParent(this);
			saturationLevel.saveIt();
		}

		return saturationLevel;
	}

	public void degradeDemandLevelsWithBaseInterval(final Integer baseInterval)
	{
		LazyList<SaturationLevel> saturationLevels = this.getAll(SaturationLevel.class);

		Observable.from(saturationLevels).subscribe(new Action1<SaturationLevel>()
		{
			public void call(SaturationLevel saturationLevel)
			{
				String type = saturationLevel.getType();
				Float defaultQuantity = 1F;

				if (type.equals("block-break"))
				{
					defaultQuantity = (2F * Bukkit.getOnlinePlayers().length);
				}

				Float demandToAdjustBy = (baseInterval / 10) * defaultQuantity;
				saturationLevel.adjustDemandByAmount(-demandToAdjustBy);
				saturationLevel.saveIt();
			}
		});
	}
}
