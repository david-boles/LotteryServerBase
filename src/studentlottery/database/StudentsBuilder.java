package studentlottery.database;

import java.util.ArrayList;

public class StudentsBuilder {
	private ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Gets all students
	 * 
	 * @return ArrayList of all students
	 */
	public ArrayList<Student> get() {
		return this.students;
	}
	
	/**
	 * Adds/sets an Student for the StudentsBuilder instance, prevents multiple values of the same ID.
	 * 
	 * @param a The Student
	 * @return The StudentsBuilder instance, allows chaining: new StudentsBuilder().add(...).add(...).add(...)
	 */
	public StudentsBuilder add(Student a) {
		for(int pos = 0; pos < students.size(); pos++) {
			if(students.get(pos).getStudentID() == a.getStudentID()) {
				students.set(pos, a);
			}
		}
		
		students.add(a);
		
		return this;
	}
}
