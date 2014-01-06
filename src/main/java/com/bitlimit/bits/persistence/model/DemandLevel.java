package com.bitlimit.bits.persistence.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/23/13
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */

@BelongsTo(parent = Market.class, foreignKeyName = "id_markets")
public class DemandLevel extends Model
{
	/*
	 *
	 *  Getters
	 *
	 */

	public Integer getDemand()
	{
		return this.getInteger("demand");
	}

	/*
	 *
	 *  Setters
	 *
	 */

	public void setDemand(Integer demand)
	{
		this.setInteger("demand", demand);
	}

	/*
	 *
	 *  Manipulation Convenience Methods
	 *
	 */

	public void adjustDemandByFactor(Integer factor)
	{
		/* Discussion: this recursive function was a first attempt.
		 * It will be revised to use a positive incrementing count which will then be fed into an arctan based function.
		 */

		this.setDemand(this.getDemand() + factor);
	}


}
