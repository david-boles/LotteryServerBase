package studentlottery.plugin;

import studentlottery.database.Attribute;
import studentlottery.server.Server;

public interface IPlugin {
	/**
	 * Gets the name of the Plugin
	 * 
	 * @return The name
	 */
	public String getName();
	
	/**
	 * Gets the version identifier of the Plugin
	 * 
	 * @return The version identifier
	 */
	public String getVersion();
	
	/**
	 * Function called by different events in the server
	 * 
	 * @param eventName The name of the event
	 * @param a Any accompanying data
	 * @param server The Server instance
	 */
	public <T> void eventOccured(String eventName, Attribute<T> a, Server server);
	
}
