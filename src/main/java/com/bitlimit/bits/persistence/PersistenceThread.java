package com.bitlimit.bits.persistence;

import com.bitlimit.bits.configuration.ConfigurationManager;
import org.javalite.activejdbc.Base;

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
		ConfigurationManager configurationManager = ConfigurationManager.getSharedManager();

		Base.open("org.postgresql.Driver", configurationManager.getPostgresURI(), configurationManager.getPostgresUsername(), configurationManager.getPostgresPassword());
	}

	private void cleanup()
	{
		/* Close the shared database connection. */
		Base.close();
	}
}
