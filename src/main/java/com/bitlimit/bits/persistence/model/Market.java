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


}
