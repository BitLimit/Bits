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
	private final ScheduledExecutorService executorService;

	/*
	 *
	 *  Designated Initializer
	 *
	 */

	public MarketManager(final Market market)
	{
		this.market = market;

		final Integer interval = 10;
		this.executorService = Executors.newScheduledThreadPool(interval, new ThreadFactory()
		{
			public Thread newThread(Runnable runnable)
			{
				return new PersistenceThread(runnable);
			}
		});
		this.executorService.scheduleAtFixedRate(new PersistenceRunnable()
		{
			public void run()
			{
				try
				{
				market.degradeDemandLevelsWithBaseInterval(interval);
				}
				catch (Exception e)
				{
					System.out.println(e.getLocalizedMessage());
				}
			}
		}, interval, interval, TimeUnit.SECONDS);

	}

}
