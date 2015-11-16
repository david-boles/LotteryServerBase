package studentlottery.database;

public interface IDataBase {

    /*
    Please note: Databases must not contain more than one student with the same ID or attribute per student with the same ID. This
    must be checked in database code to prevent loss of data.
    */

	/**
	 * Load all students
	 * 
	 * @return StudentList
	 */
	public StudentList loadStudents();
    
    /**
     * Load student
	 * 
     * @param sID ID of the student
	 * @return Student object, null if not found
	 */
	public Student loadStudent(int sID);
	
	/**
	 * Save all students, overwrites existing and adds
	 * 
	 * @param s ArrayList of Student objects
	 */
	public void saveStudents(StudentList s);
    
    /**
     * Save student, overwrites existing or adds
	 * 
	 * @param s Student object
     * @return true if overwritten, false if added
	 */
	public boolean saveStudent(Student s);
    
    /**
     * Delete students, ensures empty database
     */
    public void deleteStudents();
     
    /**
     * Delete student, ensures nonexistance
     * 
     * @param sID ID of the student
     * @return true if deleted, false if not found
     */
    public boolean deleteStudent(int sID);
	
}
