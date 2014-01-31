package com.bitlimit.bits.persistence;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/15/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceRunnable implements Runnable
{
	public void run()
	{
		/*
		 *
		 *  Discussion:
		 *  This runnable should be assumed to be running on an instance of PersistenceThread, meaning that there should be a database connection implicitly.
		 *  Furthermore, cleanup should also be left alone, as the thread should take care of it.
		 *
		 */

		assert (Thread.currentThread() instanceof PersistenceThread);
	}
}
