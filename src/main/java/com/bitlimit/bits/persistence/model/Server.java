package com.bitlimit.bits.persistence.model;

import com.bitlimit.bits.bukkit.BitsPlugin;
import com.bitlimit.pulse.Pulse;
import org.bukkit.Bukkit;
import org.javalite.activejdbc.Model;

import java.util.logging.Level;

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
			server.saveIt();

			Pulse.recordCondition(BitsPlugin.getPlugin(), "Created server model object.", Level.FINE);

			Market serverMarket = new Market();
			serverMarket.set("saturation_multiplier", 1);
			serverMarket.saveIt();

			Pulse.recordCondition(BitsPlugin.getPlugin(), "Created market model object.", Level.FINER);

			serverMarket.setParent(server);

			System.out.println("set parent");

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

	public Market getMarket()
	{
		return this.getAll(Market.class).get(0);
	}

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
