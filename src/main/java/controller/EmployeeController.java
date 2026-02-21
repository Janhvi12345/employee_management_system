package controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.dao.EmployeeDao;
import model.entities.Employee;
import model.exception.EmployeeAlreadyExistException;
import model.exception.NoRecordFoundException;
public class EmployeeController {
	//Controller - communicates between view and model
	
	EmployeeDao dao;
	public EmployeeController() {
		dao = new EmployeeDao();
	}
	
public boolean adminValidate(Employee emp){
		return dao.adminValidate(emp) ;
	}

public boolean checkIfEmployeeExists(String email, Long phone) {
	return dao.checkIfEmployeeExists(email, phone);
}

public boolean addEmployee(Employee emp) {
	//check if employee already exists
	if(checkIfEmployeeExists(emp.getEmail(), emp.getPhone()) != true) {
		return dao.addEmployee(emp);
	}
	else {
		throw new EmployeeAlreadyExistException("Employee with the provided email or phone already exists");
	}
}

public boolean deleteEmployee(int id) {
	// check if employee exist or not
	if(viewEmployee(id)!=null) {
		return dao.deleteEmployee(id);
	}
	
	else {
		throw new NoRecordFoundException("Employee doesn't exist");
	}
}

public Employee viewEmployee(int id) {
	return dao.viewEmployee(id);
}

public List<Employee> getAllEmployee(){
	return dao.getAllEmployee();
}

public boolean updateEmployee(Employee emp) {
	return dao.updateEmployee(emp);
}

public int addMultipleEmployee(List<Employee> empList) {
	
	Iterator<Employee> itr = empList.iterator();
	while(itr.hasNext()) {
		Employee emp = itr.next();
		
		if(checkIfEmployeeExists( emp.getEmail(),emp.getPhone())) {
			itr.remove();
		}
	}
	return dao.addMultipleEmployee(empList);
	
}
}


