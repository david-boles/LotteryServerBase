package studentlottery.database;

import java.util.ArrayList;

public class Student {
	private int sID;
	private ArrayList<Attribute<? extends Object>> attributes = new ArrayList<Attribute<? extends Object>>();
	
	/**
	 * Initialize a new Student
	 * 
	 * @param sID The ID of the new Student instance
	 */
	public Student(int sID) {
		this.sID = sID;
	}
	
	/**
	 * Initialize a new Student
	 * 
	 * @param sID The ID of the new Student instance
	 * @param attributes The attributes of the new Student instance
	 */
	public Student(int sID, AttributesBuilder b) {
		this.sID = sID;
		this.attributes = b.get();
	}
	
	/**
	 * Gets an attribute from the Student instance, casts Attribute's attribute to <T>
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
	 * Gets all attributes
	 * 
	 * @return ArrayList of all attributes, DO NOT MODIFY THIS VAR!!!
	 */
	public ArrayList<Attribute<? extends Object>> getAttributes() {
		return attributes;
	}
	
	/**
	 * Adds/sets an Attribute for the Student instance, prevents multiple values of the same ID.
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
	
	/**
	 * Gets the student ID of the Student instance
	 * 
	 * @return the Student instance's student ID
	 */
	public int getStudentID() {
		return this.sID;
	}
	
	/**
	 * Sets the student ID of the Student instance
	 * 
	 * @param sID The new ID for the Student instance
	 */
	public void setStudentID(int sID) {
		this.sID = sID;
	}
	
}
