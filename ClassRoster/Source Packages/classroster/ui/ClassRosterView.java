package classroster.ui;
import java.util.*;
import classroster.dto.Student;

public class ClassRosterView {
	private UserIO io;
	
	public ClassRosterView(UserIO io) {
		this.io = io;
	}

	public int printMenuAndGetSelection() {
	
		int menuSelection = 0;
		
		io.print("Main Menu");
		io.print("1: List Student IDs");
		io.print("2: Create New Student");
		io.print("3: View a Student");
		io.print("4: Remove a Student");
		io.print("5: Exit");
		
		menuSelection = io.readInt("Please select from above choices", 1, 5);
		
		return menuSelection;
	}
	
	public Student getNewStudentInfo() {
		String studentId = io.readString("Please enter student ID");
		String firstName = io.readString("Please enter First Name");
		String lastName = io.readString("Please enter Last Name");
		String cohert = io.readString("Please enter cohert");
		
		Student currentStudent = new Student(studentId);
		currentStudent.setFirstName(firstName);
		currentStudent.setLastName(lastName);
		currentStudent.setCohert(cohert);
		
		return currentStudent;
	}
	
	public void displayCreateStudentBanner() {
		io.print("=== Create Student ===");
	}
	
	public void displayCreateSuccessBanner() {
		io.readString("Student successfully created. Please hit enter to continue");
	}
	
	public void displayStudentList(List<Student> studentList) {
		for(Student currentStudent: studentList) {
			String studentInfo = String.format("#%s : %s %s", currentStudent.getStudentId(), currentStudent.getFirstName(), currentStudent.getLastName());
			io.print(studentInfo);
			io.print("");
		}
		
		io.readString("Please hit enter to continue");
	}
	
	public void displayDisplayAllBanner() {
	    io.print("=== Display All Students ===");
	}
	
	public void displayDisplayStudentBanner () {
	    io.print("=== Display Student ===");
	}
	
	public String getStudentIdChoice() {
		return io.readString("Please enter the Student ID");
	}
	
	public void displayStudent(Student student) {
		if(student != null) {
			String studentInfo = String.format("#%s : %s %s", student.getStudentId(), student.getFirstName(), student.getLastName());
			io.print(studentInfo);
			io.print("");
		}else {
			io.print("No such student");
		}
		
		io.readString("Please hit enter to continue");
	}
	
	
	public void displayRemoveStudentBanner () {
	    io.print("=== Remove Student ===");
	}
	
	public void displayRemoveResult(Student studentRecord) {
		if(studentRecord != null) {
			io.print("Student successfully removed");
		}else {
			io.print("No such student");
		}
		
		io.readString("Please hit enter to continue");
	}
	
	public void displayExitBanner() {
	    io.print("Good Bye!!!");
	}
	
	public void displayUnknownCommandBanner() {
	    io.print("Unknown Command!!!");
	}
	
	public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	










