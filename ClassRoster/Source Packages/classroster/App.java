package classroster;

import classroster.controller.ClassRosterController;
import classroster.dao.ClassRosterAuditDao;
import classroster.dao.ClassRosterAuditDaoFileImpl;
import classroster.dao.ClassRosterDao;
import classroster.dao.ClassRosterDaoFileImpl;
import classroster.service.ClassRosterServiceLayer;
import classroster.service.ClassRosterServiceLayerImpl;
import classroster.ui.ClassRosterView;
import classroster.ui.UserIO;
import classroster.ui.UserIOConsoleImpl;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserIO myIo = new UserIOConsoleImpl();
		ClassRosterView myView = new ClassRosterView(myIo);
		ClassRosterDao myDao = new ClassRosterDaoFileImpl();
		ClassRosterAuditDao myAduitDao = new ClassRosterAuditDaoFileImpl();
		ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAduitDao);
		ClassRosterController controller = new ClassRosterController(myView, myService);
		controller.run();
	}

}
