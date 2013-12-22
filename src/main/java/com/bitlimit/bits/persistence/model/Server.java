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
	public static Server getServer(String serverName)
	{
		Server server = Server.findFirst("name = ?", serverName);

		if (server == null)
		{
			server = new Server();
			server.set("name", serverName);

			Market serverMarket = new Market();
			serverMarket.insert();

			server.add(serverMarket);
			server.insert();
		}

		return server;
	}

	public static Server getCurrentServer()
	{
		return getServer(Bukkit.getServerName());
	}
}
