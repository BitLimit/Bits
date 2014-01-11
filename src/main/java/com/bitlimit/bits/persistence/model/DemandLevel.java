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

	public String getType()
	{
		return this.getString("type");
	}

	public Float getDemand()
	{
		return this.getFloat("demand");
	}

	/*
	 *
	 *  Setters
	 *
	 */

	public void setDemand(Float demand)
	{
		if (demand < 0F)
		{
			demand = 0F;
		}

		this.setFloat("demand", demand);
	}

	/*
	 *
	 *  Manipulation Convenience Methods
	 *
	 */

	public void adjustDemandByAmount(Float amount)
	{
		/* Discussion: this recursive function was a first attempt.
		 * It will be revised to use a positive incrementing count which will then be fed into an arctan based function.
		 */

		this.setDemand(this.getDemand() + amount);

		/* Increment x-value so the y-value can be calculated formulaically and without the absolute uncontrollable nature of forced recursion. */
	}

	public Float getCurrentValuation()
	{
		Float x = this.getDemand();/* x */
		Float halfLife = 1F/5F; /* TODO: read from prefererences. */

		return (float)(2F * (-Math.atan((1F/halfLife) * x) / Math.PI) + 1F);
	}
}
