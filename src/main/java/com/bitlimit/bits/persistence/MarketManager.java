package com.bitlimit.bits.persistence;

import com.bitlimit.bits.persistence.model.Market;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/23/13
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarketManager
{
	private final Market market;

	/*
	 *
	 *  Designated Initializer
	 *
	 */

	public MarketManager(final Market market)
	{
		this.market = market;
	}

}
