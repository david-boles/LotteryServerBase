package studentlottery.database;

import java.util.ArrayList;

public class StudentList {
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public StudentList(StudentsBuilder sB) {//CHANGE
		this.students = sB.get();
	}
	
	/**
	 * Gets an student from the StudentList instance
	 * 
	 * @param sID ID of the Student
	 * @return Casted Student if one with ID sID is found, otherwise null
	 */
	public Student getStudent(int sID) {
		for(int pos = 0; pos < students.size(); pos++) {
			if(students.get(pos).getStudentID() == sID)
				return students.get(pos);
		}
		
		return null;
	}
	
	/**
	 * Gets all students, DO NOT MODIFY THIS VAR!!!
	 * 
	 * @return ArrayList of all attributes
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	/**
	 * Adds/sets an Student for the StudentList instance, prevents multiple students of the same ID.
	 * 
	 * @param s The Student
	 * @return True if overwrote existing Student, false otherwise
	 */
	public boolean setStudent(Student s) {
		for(int pos = 0; pos < students.size(); pos++) {
			if(students.get(pos).getStudentID() == s.getStudentID()) {
				students.set(pos, s);
				return true;
			}
		}
		
		students.add(s);
		return false;
	}
}
