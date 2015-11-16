package studentlottery.database;

import java.util.ArrayList;

public class AttributesBuilder {
	private ArrayList<Attribute<? extends Object>> attributes = new ArrayList<Attribute<? extends Object>>();
	
	/**
	 * Gets all attributes
	 * 
	 * @return ArrayList of all attributes
	 */
	public ArrayList<Attribute<? extends Object>> get() {
		return this.attributes;
	}
	
	/**
	 * Adds/sets an Attribute for the AttributeBuilder instance, prevents multiple values of the same ID.
	 * 
	 * @param a The Attribute
	 * @return The AttributeBuilder instance, allows chaining: new AttributeBuilder().add(...).add(...).add(...)
	 */
	public AttributesBuilder add(Attribute<? extends Object> a) {
		for(int pos = 0; pos < attributes.size(); pos++) {
			if(attributes.get(pos).getAID().equals(a.getAID())) {
				attributes.set(pos, a);
			}
		}
		
		attributes.add(a);
		
		return this;
	}

}