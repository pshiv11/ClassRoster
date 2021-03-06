package classroster.dao.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classroster.dao.ClassRosterAuditDao;
import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dto.Student;
import classroster.service.ClassRosterDuplicateIdException;
import classroster.service.ClassRosterDataValidationException;
import classroster.service.ClassRosterServiceLayer;
import classroster.service.ClassRosterServiceLayerImpl;

class ClassRosterServiceLayerImplTest {

	private ClassRosterServiceLayer service;
	
	public ClassRosterServiceLayerImplTest() {
		ClassRosterDao dao = new ClassRosterDaoStubImpl();
		ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
		
		service = new ClassRosterServiceLayerImpl(dao, auditDao);
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateValidStudent() {
		//ARRANGE
		Student student = new Student("0002");
		student.setFirstName("Charlse");
		student.setFirstName("Babbage");
		student.setCohert(".NET-May-1845");
		
		//ACT
		try {
			service.createStudent(student);
		} catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException | ClassRosterPersistenceException e) {
			fail("Student was valid. No exception should have been thrown");
		}
				
	}
	
	@Test
	public void testCreateDuplicateIdStudent() {
		
		//ARRANGE
		Student student = new Student("0002");
		student.setFirstName("Charlse");
		student.setFirstName("Babbage");
		student.setCohert(".NET-May-1845");
		
		//ACT
		try {
			service.createStudent(student);
			fail("Expected Duplicate ID Exception was not thrown");
		} catch(ClassRosterDataValidationException | ClassRosterPersistenceException e) {
			//ASSERT
			fail("Incorrect exception was thrown");
		} catch (ClassRosterDuplicateIdException e) {
			return;
		}
	}
	
	@Test
	public void testCreateStudentInvalidData() {
		// ARRANGE
	    Student student = new Student("0002");
	    student.setFirstName("");
	    student.setLastName("Babbage");
	    student.setCohert(".NET-May-1845");
	    
	    //ACT
	    try {
	    	service.createStudent(student);
	    	fail("Expected ValidationException was not thrown");
	    }catch(ClassRosterDuplicateIdException
	            | ClassRosterPersistenceException e) {
	    	fail("Incorrect exception was thrown");
	    }catch(ClassRosterDataValidationException e) {
	    	return;
	    }
	}
	
	@Test
	public void testGetAllStudents() throws Exception{
		//ARRANGE
		
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohert("Java-May-1845");
        
        //ACT and ASSERT
        assertEquals(1, service.getAllStudents().size(), "Should only have one student");
        assertTrue(service.getAllStudents().contains(testClone), "The one student shoukd be Ada");
	}
	
	
	@Test
	public void testGetStudent() throws Exception {
	    // ARRANGE
	    Student testClone = new Student("0001");
	        testClone.setFirstName("Ada");
	        testClone.setLastName("Lovelace");
	        testClone.setCohert("Java-May-1845");

	    // ACT & ASSERT
	    Student shouldBeAda = service.getStudent("0001");
	    assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
	    assertEquals( testClone, shouldBeAda,
	                                   "Student stored under 0001 should be Ada.");

	    Student shouldBeNull = service.getStudent("0042");    
	    assertNull( shouldBeNull, "Getting 0042 should be null.");

	}
	
	
	@Test
	public void testRemoveStudent() throws Exception {
	    // ARRANGE
	    Student testClone = new Student("0001");
	        testClone.setFirstName("Ada");
	        testClone.setLastName("Lovelace");
	        testClone.setCohert("Java-May-1845");

	    // ACT & ASSERT
	    Student shouldBeAda = service.removeStudent("0001");
	    assertNotNull( shouldBeAda, "Removing 0001 should be not null.");
	    assertEquals( testClone, shouldBeAda, "Student removed from 0001 should be Ada.");

	    Student shouldBeNull = service.removeStudent("0042");    
	    assertNull( shouldBeNull, "Removing 0042 should be null.");

	}
	
	
	
	
	
	
	
	
	
	
	

}
