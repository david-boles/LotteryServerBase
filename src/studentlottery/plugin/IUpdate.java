package studentlottery.plugin;

import studentlottery.server.Server;

public interface IUpdate {
	/**
	 * Gets the name of the state update
	 * 
	 * @return The name
	 */
	public String getName();
	
	/**
	 * Runs the update's code
	 * 
	 * @param s The Server instance
	 */
	void runUpdate(Server s);
}
