package classroster.dao.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterDaoFileImpl;
import classroster.dto.Student;

class ClassRosterDaoFileImplTest {
	
	ClassRosterDao testDao;

	public ClassRosterDaoFileImplTest() {
		
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		String testFile = "testroster.txt";
		new FileWriter(testFile);
		testDao= new ClassRosterDaoFileImpl(testFile);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddGetStudent() throws Exception {
		//create our test method inputs
		
		String studentId = "0001";
		Student student = new Student(studentId);
		student.setFirstName("Ada");
		student.setLastName("Lovelace");
		student.setCohert("Java-May-1845");
		
		
	//Add the student to DAO
		this.testDao.addStudent(studentId,  student);
		
	//get the student from DAO
		Student retrievedStudent = testDao.getStudent(studentId);
		
	//check the data is equal
		assertEquals(student.getStudentId(), retrievedStudent.getStudentId(), "Checking Student ID");
		assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking first name");
		assertEquals(student.getCohert(), retrievedStudent.getCohert(), "Checking cohert");
	}

	
	@Test
	public void testAddGetAllStudents() throws Exception {
		
		//create first student
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Locelace");
		firstStudent.setCohert("Java-May-1845");
		
		//create second student
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohert(".NET-May-1845");
		
		//Add both students to DAO
		testDao.addStudent(firstStudent.getStudentId(), firstStudent);
		testDao.addStudent(secondStudent.getStudentId(), secondStudent);
		
		//Retrieve the list of all students within the DAO
		List<Student> allStudents = testDao.getAllStudents();
		
		//First check the general contents of the list
		assertNotNull(allStudents, "The list of students must not be null");
		assertEquals(2, allStudents.size(), "List of students should have 2 students");
		
		//then the specifics
		assertTrue(testDao.getAllStudents().contains(firstStudent), "The list of students should include Ada");
		assertTrue(testDao.getAllStudents().contains(secondStudent), "The list of students should include Charles");
			
	}
	
	
	@Test
	public void testRemoveStudent() throws Exception {
		
		//create first student
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Locelace");
		firstStudent.setCohert("Java-May-1845");
				
		//create second student
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohert(".NET-May-1845");
		
		//Add both students to DAO
		testDao.addStudent(firstStudent.getStudentId(), firstStudent);
		testDao.addStudent(secondStudent.getStudentId(), secondStudent);
		
		//remove the first student - Ada
		Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());
		
		//check the correct object was removed
		assertEquals(removedStudent, firstStudent, "The removed student should be Ada");
		
		//Get all the students
		List<Student> allStudents = testDao.getAllStudents();
		
		//first check the general contenst of the list
		assertNotNull(allStudents, "All students list should not be null");
		assertEquals(1, allStudents.size(), "All students should only have 1 student");
		
		//Then the specifies
		assertFalse(allStudents.contains(firstStudent), "All students should NOT include Ada");
		assertTrue(allStudents.contains(secondStudent), "All students should include Charles");
		
		//remove the second student - Charles
		removedStudent = testDao.removeStudent(secondStudent.getStudentId());
		
		//check that the correct student was removed
		assertEquals(removedStudent, secondStudent, "The removed student should be charles");
		
		//retrieve all of the students again, and check the list
		allStudents = testDao.getAllStudents();
		
		//check the contents of the list - it should be empty
		assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty");
		
		//try to get both students by their old id - they should be null
		
		Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
		assertNull(retrievedStudent, "Ada was removed, should be null");
		
		retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
		assertNull(retrievedStudent, "Charles was removed, should be null");
		
	
		
	}
	
	
	
	
	
	
	
	
}
