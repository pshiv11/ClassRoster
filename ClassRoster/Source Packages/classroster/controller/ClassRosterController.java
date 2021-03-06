package classroster.controller;

import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterPersistenceException;
import classroster.dao.ClassRosterDaoFileImpl;
import classroster.dto.Student;
import classroster.service.ClassRosterDataValidationException;
import classroster.service.ClassRosterDuplicateIdException;
import classroster.service.ClassRosterServiceLayer;
import classroster.ui.ClassRosterView;
import classroster.ui.UserIO;
import classroster.ui.UserIOConsoleImpl;
import java.util.*;

public class ClassRosterController {
	
	private ClassRosterView view;
	private ClassRosterServiceLayer service;
	
	
	
	
	public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
		this.view = view;
		this.service = service;
	}

	
	public void run() {
		boolean keepGoing = true;
		int menuSelection = 0;
		
		try {
			while(keepGoing) {
				menuSelection = this.getMenuSelection();
				switch(menuSelection) {
				
					case 1:
						this.listStudents();
						break;
					case 2:
						this.createStudent();	
						break;
					case 3:
						this.viewStudent();
						break;
					case 4:
						this.removeStudent();
						break;
					case 5:
						keepGoing = false;
						break;
						
					default:
						
						this.unknownCommand();	
				}
				
			}
		}catch(ClassRosterPersistenceException e) {
			view.displayErrorMessage(e.getMessage());
		}
			
		
		this.exitMessage();
	}
	
	private int getMenuSelection() {
		return view.printMenuAndGetSelection();
	}
	
	private void createStudent() throws ClassRosterPersistenceException {
		view.displayCreateStudentBanner();
		boolean hasErrors = false;
		
		do {
			Student currentStudent = view.getNewStudentInfo();
			try {
				service.createStudent(currentStudent);
				view.displayCreateSuccessBanner();
				hasErrors = false;
			}catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
				hasErrors = true;
				view.displayErrorMessage(e.getMessage());
			}
		}while(hasErrors);
		
	}
	
	private void listStudents() throws ClassRosterPersistenceException {
		view.displayDisplayAllBanner();
		List<Student> studentList = service.getAllStudents();
		view.displayStudentList(studentList);
	}
	
	private void viewStudent() throws ClassRosterPersistenceException {
		view.displayDisplayStudentBanner();
		String studentId = view.getStudentIdChoice();
		Student student = service.getStudent(studentId);
		view.displayStudent(student);
	}
	
	private void removeStudent() throws ClassRosterPersistenceException {
		view.displayRemoveStudentBanner();
		String studentId = view.getStudentIdChoice();
		Student removedStudent = service.removeStudent(studentId);
		view.displayRemoveResult(removedStudent);
	}
	
	private void unknownCommand() {
		view.displayUnknownCommandBanner();
	}
	
	private void exitMessage() {
		view.displayExitBanner();
	}
	
	
	
	
	
	
	
	
	
	
	
}
