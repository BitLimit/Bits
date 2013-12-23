package com.bitlimit.bits.persistence.model;

import org.bukkit.Bukkit;
import org.javalite.activejdbc.Model;

/**
 * Created with IntelliJ IDEA.
 * User: kolin
 * Date: 12/21/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Model
{
	/*
	 *
	 *  Designated Initializers
	 *
	 */

	public static Server getServer(String serverName)
	{
		Server server = Server.findFirst("name = ?", serverName);

		if (server == null)
		{
			server = new Server();
			server.setName(serverName);
			server.insert();

			Market serverMarket = new Market();
			serverMarket.insert();

			serverMarket.setParent(server);
			serverMarket.saveIt();
		}

		return server;
	}

	public static Server getCurrentServer()
	{
		return getServer(Bukkit.getServerName());
	}

	/*
	 *
	 *  Getters
	 *
	 */

	public String getName()
	{
		return this.getString("name");
	}

	/*
	 *
	 *  Setters
	 *
	 */

	public void setName(String name)
	{
		this.setString("name", name);
	}

}
