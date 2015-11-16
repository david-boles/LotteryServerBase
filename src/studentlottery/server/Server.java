package studentlottery.server;

import java.util.ArrayList;

import studentlottery.database.Attribute;
import studentlottery.plugin.IPlugin;
import studentlottery.plugin.IUpdate;

public class Server {
	private ArrayList<Attribute<? extends Object>> attributes;
	private ArrayList<IPlugin> plugins;
	private ArrayList<IUpdate> stateUpdates;
	private int state;
	private boolean isRunning = false;
	
	public Server(ArrayList<Attribute<? extends Object>> attributes, ArrayList<IPlugin> plugins, ArrayList<IUpdate> stateUpdates, int initialState) {
		this.state = initialState;
		
		for (int i = 0; i < stateUpdates.size(); i++) this.addStateUpdate(stateUpdates.get(i));
		for (int i = 0; i < attributes.size(); i++) this.setAttribute(attributes.get(i));
		for (int i = 0; i < plugins.size(); i++) this.setPlugin(plugins.get(i));

		this.newEvent("serverInit", null);
	}
	
	//Attribute setters and getters
	
	/**
	 * Gets an attribute from the Server instance, casts Attribute's attribute to <T>
	 * 
	 * @param aID ID of the Attribute
	 * @return Casted Attribute if one with ID aID is found, otherwise null
	 */
	@SuppressWarnings("unchecked")
	public <T> Attribute<T> getAttribute(String aID) {
		for(int pos = 0; pos < attributes.size(); pos++) {
			if(attributes.get(pos).getAID().equals(aID))
				return (Attribute<T>) attributes.get(pos);
		}
		
		return null;
	}
	
	/**
	 * Gets all attributes, DO NOT MODIFY THIS VAR!!!
	 * 
	 * @return ArrayList of all attributes
	 */
	public ArrayList<Attribute<? extends Object>> getAttributes() {
		return attributes;
	}
	
	/**
	 * Adds/sets an Attribute for the Server instance, prevents multiple values of the same ID.
	 * 
	 * @param a The Attribute
	 * @return True if overwrote existing Attribute, false otherwise
	 */
	public boolean setAttribute(Attribute<? extends Object> a) {
		for(int pos = 0; pos < attributes.size(); pos++) {
			if(attributes.get(pos).getAID().equals(a.getAID())) {
				attributes.set(pos, a);
				return true;
			}
		}
		
		attributes.add(a);
		return false;
	}
	
	//Plugin setters and getters
	
	/**
	 * Gets a plugin from the Server instance
	 * 
	 * @param name Name of the Plugin
	 * @return Casted Plugin if one with name name is found, otherwise null
	 */

	public IPlugin getPlugin(String name) {
		for(int pos = 0; pos < plugins.size(); pos++) {
			if(plugins.get(pos).getName().equals(name))
				return plugins.get(pos);
		}
		
		return null;
	}
	
	/**
	 * Gets all plugins, DO NOT MODIFY THIS VAR!!!
	 * 
	 * @return ArrayList of all plugins
	 */
	public ArrayList<IPlugin> getPlugins() {
		return this.plugins;
	}
	
	/**
	 * Adds/sets an Plugin for the Server instance, prevents multiple values of the same name.
	 * 
	 * @param a The Plugin
	 * @return True if overwrote existing Plugin, false otherwise
	 */
	public boolean setPlugin(IPlugin p) {
		for(int pos = 0; pos < plugins.size(); pos++) {
			if(plugins.get(pos).getName().equals(p.getName())) {
				plugins.set(pos, p);
				return true;
			}
		}
		
		plugins.add(p);
		return false;
	}
	
	//State functions setters and getters
	
	/**
	 * Gets an state Update from the Server instance
	 * 
	 * @param state The state
	 * @return Update of that state
	 */
	public IUpdate getStateUpdate(int state) {
		return this.stateUpdates.get(state);
	}
	
	/**
	 * Gets all state Updates, DO NOT MODIFY THIS VAR!!!
	 * 
	 * @return ArrayList of all state Updates
	 */
	public ArrayList<IUpdate> getStateUpdates() {
		return this.stateUpdates;
	}
	
	/**
	 * Adds/sets an state Update for the Server instance
	 * 
	 * @param r The new Update
	 * @return Its state
	 */
	public int addStateUpdate(IUpdate r) {
		stateUpdates.add(r);
		attributes.add(new Attribute<Integer>("update_" + r.getName(), stateUpdates.size()-1));
		return stateUpdates.size()-1;
	}

	//State setters and getters
	
	/**
	 * State getter
	 * 
	 * @return the Server's state
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * State setter
	 * 
	 * @param state The server's new state
	 */
	public void setState(int state) {
		this.state = state;
	}

	//Start and stop server updates
	
	/**
	 * Sets isRunning to true, starts server
	 */
	public void startServer() {
		this.isRunning = true;
		this.newEvent("serverStart", null);
		while(this.isRunning) {
			this.stateUpdates.get(this.state).runUpdate(this);
		}
	}
	
	/**
	 * Sets isRunning to false, breaks loop in startServer
	 */
	public void stopServer() {
		this.isRunning = false;
		this.newEvent("serverStop", null);
	}

	//Other
	
	/**
	 * Function calls all plugins' eventOccured methods
	 * 
	 * @param eventName The name of the event
	 * @param a Any accompanying data
	 */
	public <T> void newEvent(String eventName, Attribute<T> a) {
		for(int i = 0; i < this.plugins.size(); i++) {
			this.plugins.get(i).eventOccured(eventName, a, this);
		}
	}
}
