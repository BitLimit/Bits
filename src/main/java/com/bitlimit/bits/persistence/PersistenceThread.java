package com.bitlimit.bits.persistence;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/15/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistenceThread extends Thread
{
	PersistenceThread(Runnable target)
	{
		super(target);
	}

	@Override
	public void run()
	{
		this.setup();

		super.run();

		this.cleanup();
	}

	private void setup()
	{
		/* Create a database connection for the runnables to use. */
	}

	private void cleanup()
	{
		/* Close the shared database connection. */
	}
}
