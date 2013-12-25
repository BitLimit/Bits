package com.bitlimit.bits.persistence;

import com.bitlimit.bits.persistence.model.Market;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
	private final ExecutorService executorService;

	/*
	 *
	 *  Designated Initializer
	 *
	 */

	public MarketManager(Market market)
	{
		this.market = market;

		this.executorService = Executors.newScheduledThreadPool(1, new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				return new PersistenceThread(runnable);
			}
		});
	}

}
