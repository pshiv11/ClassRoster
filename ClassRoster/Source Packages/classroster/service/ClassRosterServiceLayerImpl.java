package classroster.service;

import java.util.List;

import classroster.dao.ClassRosterAuditDao;
import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
	
	ClassRosterDao dao;
	private ClassRosterAuditDao auditDao;
	

	public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
	}

	@Override
	public void createStudent(Student student) throws ClassRosterDuplicateIdException,
			ClassRosterDataValidationException, ClassRosterPersistenceException {
		
		//first check to see if student with @id already exists. If it does, we are done here
		if(dao.getStudent(student.getStudentId()) != null) {
			throw new ClassRosterDuplicateIdException("ERROR: Could not create student. Student id " + student.getStudentId() + " already exists");
		}
		
		//now we will validate all fields on the given student object and if its valid, we will proceed
		this.validateStudentData(student);
		
		//at this step we passed all validation rules and we are ready to persist student object
		
		dao.addStudent(student.getStudentId(), student);	
		auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
		
	}

	
	//this is a pass-through method
	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
	
		return dao.getAllStudents();
	}

	
	//this is a pass-through method
	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		return dao.getStudent(studentId);
	}

	
	//this is a pass-through method
	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
		Student removedStudent = dao.removeStudent(studentId);
		auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
		return removedStudent;
	}
	
	
	private void validateStudentData(Student student) throws ClassRosterDataValidationException {
		if(student.getFirstName() == null
				|| student.getFirstName().trim().length() == 0
				|| student.getLastName() == null
				|| student.getLastName().trim().length() == 0
				|| student.getCohert() == null
				|| student.getCohert().trim().length() == 0) {
			
			
			throw new ClassRosterDataValidationException("ERROR: All fields [First Name, Last Name, Cohert] are required.");
		}
	
	}

}
