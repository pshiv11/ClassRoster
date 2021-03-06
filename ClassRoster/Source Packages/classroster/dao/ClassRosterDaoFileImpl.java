package classroster.dao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classroster.dto.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
	
	public static String ROSTER_FILE;
	public static final String DELIMETER = "::";
	
	private Map<String, Student> students = new HashMap<>();
	
	public ClassRosterDaoFileImpl() {
		ROSTER_FILE = "roster.txt";
	}
	
	public ClassRosterDaoFileImpl(String rosterTextFile) {
		ROSTER_FILE = rosterTextFile;
	}
	

	@Override
	public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
		// TODO Auto-generated method stub
		loadRoster();
		Student prevStudent = students.put(studentId, student);
		writeRoster();
		return prevStudent;
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		// TODO Auto-generated method stub
		loadRoster();
		return new ArrayList<Student>(students.values());
	}

	@Override
	public Student getStudent(String studentId) throws ClassRosterPersistenceException {
		// TODO Auto-generated method stub
		loadRoster();
		return students.get(studentId);
	}

	@Override
	public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
		// TODO Auto-generated method stub
		loadRoster();
		Student removedStudent = students.remove(studentId);
		writeRoster();
		return removedStudent;
	}
	
	private Student unMarshallStudent(String studentAsText) {
		String [] studentTokens = studentAsText.split(DELIMETER);
		String studentId = studentTokens[0];
		String firstName = studentTokens[1];
		String lastName = studentTokens[2];
		String cohert = studentTokens[3];
		Student studentFromFile = new Student(studentId);
		studentFromFile.setFirstName(firstName);
		studentFromFile.setLastName(lastName);
		studentFromFile.setCohert(cohert);
		
		return studentFromFile;
	}
	
	private void loadRoster() throws ClassRosterPersistenceException {
		Scanner scan;
		
		try {
			scan= new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
		}catch(FileNotFoundException e) {
			throw new ClassRosterPersistenceException("-_- Could not load roster data into memory.", e);
		}
		
		String currentLine;
		Student currentStudent;
		
		while(scan.hasNextLine()) {
			currentLine = scan.nextLine();
			currentStudent = this.unMarshallStudent(currentLine);
			students.put(currentStudent.getStudentId(), currentStudent);
		}
		scan.close();
	}
	
	
	private String marshallStudent(Student studentObj) {
		String studentAsText = studentObj.getStudentId() + DELIMETER;
		studentAsText += studentObj.getFirstName() + DELIMETER;
		studentAsText += studentObj.getLastName() + DELIMETER;
		studentAsText += studentObj.getCohert();
		
		return studentAsText;
	}
	
	
	private void writeRoster() throws ClassRosterPersistenceException {
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(ROSTER_FILE));
		}catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not save student data", e);
		}
		
		String studentAsText;
		List<Student> studentList = this.getAllStudents();
		
		for(Student currentStudent: studentList) {
			studentAsText = this.marshallStudent(currentStudent);
			out.println(studentAsText);
			out.flush();
		}
		
		out.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
