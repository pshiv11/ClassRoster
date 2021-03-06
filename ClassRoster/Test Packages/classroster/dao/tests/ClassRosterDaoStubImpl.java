package classroster.dao.tests;

import java.util.ArrayList;
import java.util.List;

import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;

public class ClassRosterDaoStubImpl implements ClassRosterDao {
	
	public Student onlyStudent;
	
	public ClassRosterDaoStubImpl() {
		onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Ada");
        onlyStudent.setLastName("Lovelace");
        onlyStudent.setCohert("Java-May-1845");
	}
	
	

	public ClassRosterDaoStubImpl(Student onlyStudent) {
		this.onlyStudent = onlyStudent;
	}



	@Override
	public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId())){
			return onlyStudent;
		} else {
			return null;
		}
		
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		List<Student> studentList = new ArrayList<>();
		studentList.add(onlyStudent);
		return studentList;
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId())){
			return onlyStudent;
		} else {
			return null;
		}
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
		if(studentId.equals(onlyStudent.getStudentId())){
			return onlyStudent;
		} else {
			return null;
		}
	}

}
