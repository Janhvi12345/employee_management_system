package view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.EmployeeController;
import model.entities.Employee;
import model.exception.EmployeeAlreadyExistException;
import model.exception.NoRecordFoundException;

public class App {
	// the app class will communicate with the EmployeeController
EmployeeController controller;
Scanner sc;

public App() {
	controller = new EmployeeController();
	sc = new Scanner(System.in);
}

public boolean adminValidate() {
	System.out.println("Enter the user id:");
	int id=sc.nextInt();
	sc.nextLine();
	
	System.out.println("Enter the password:");
	String password = sc.nextLine();
	
	Employee emp = new Employee();
	emp.setId(id);
	emp.setPassword(password);
	return controller.adminValidate(emp);
}

public boolean addEmployee() {
	System.out.println("Enter the name:");
	String name = sc.nextLine();
	
	System.out.println("Enter the salary:");
	Double salary = sc.nextDouble();
	sc.nextLine();
	
	System.out.println("Enter the email:");
	String email = sc.nextLine();
	
   System.out.println("Enter the phone:");
   Long phone = sc.nextLong();
   sc.nextLine();
   
   String password = name + "1234";
   
   
   System.out.println("Enter the Date of birth 'yyyy-mm-dd");
   String dob = sc.nextLine();
   Date dob2 = Date.valueOf(dob);
   
   System.out.println("Enter the role:");
   String role = sc.nextLine();
   
   
   Employee emp = new Employee(name, salary, email, phone, password, dob2,role);
   
   return controller.addEmployee(emp);
   
}

public boolean deleteEmployee() {
	System.out.println("Enter the id of the employee you want to delete from database:");
	int id = sc.nextInt();
	sc.nextLine();
	
//	System.out.println("Enter your mail:");
//	String email = sc.nextLine();
//	
//	System.out.println("Enter your Phone:");
//	Long phone = sc.nextLong();
//	sc.nextLine();
//	
//	Employee emp =  new Employee();
//	emp.setId(id);
//	emp.setEmail(email);
//	emp.setPhone(phone);

	return controller.deleteEmployee(id);
}


public Employee viewEmployee() {
	System.out.println("Enter the id:");
	int id = sc.nextInt();
	sc.nextLine();
	
//	Employee emp = new Employee();
//	emp.setId(id);
	
	return controller.viewEmployee(id);
	
	
	
}

public boolean updateEmployee() {
	Employee emp = viewEmployee();
	
	if(emp!=null) {
		System.out.println(emp);
		
		System.out.println("Enter the name to update or press enter");
		String name = sc.nextLine();
		
		System.out.println("Enter the salary to update or press enter");
		String salary = sc.nextLine();
		
		System.out.println("Enter the email to update or press enter");
		String email = sc.nextLine();
		
		System.out.println("Enter the phone to update or press enter");
		String phone = sc.nextLine();
		
		System.out.println("Enter the password to update or press enter");
		String password = sc.nextLine();
		
		System.out.println("Enter the dob to update or press enter");
		String dob = sc.nextLine();
		
		System.out.println("Enter the role to update or press enter");
		String role = sc.nextLine();
		
		if(!name.isEmpty()) {
			emp.setName(name);
		}
		
		if(!salary.isEmpty()) {
			emp.setSalary(Double.parseDouble(salary));
		}
		if(!email.isEmpty()) {
			emp.setEmail(email);
		}
		if(!phone.isEmpty()) {
			emp.setPhone(Long.parseLong(phone));
		}
		if(!password.isEmpty()) {
			emp.setPassword(password);
		}
		
		if(!dob.isEmpty()) {
			emp.setDob(Date.valueOf(dob));
		}
		if(!role.isEmpty()) {
			emp.setRole(role);
		}
		
		return controller.updateEmployee(emp);
		
	}
	else {
		throw new NoRecordFoundException("Employee doesn't exist");
	}
	
}

public List<Employee> getAllEmployee(){
	return controller.getAllEmployee();
}


public int addMultipleEmployee() {
	// we need to make a method in view, model and controller which basically first ask user how many employee to add then it create a list add all employee in that list and then return that list to controller then controller checks for every employee object that whether it is already present or not if it is already present it will discard it if not i will call model and go for batch execution
	System.out.println("How many employees you have to add:");
	int number = sc.nextInt();
	sc.nextLine();
	
	List<Employee> empList = new ArrayList<Employee>();
	for(int i = 0; i<number; i++) {
	System.out.println("Enter the name:");
	String name = sc.nextLine();
	
	System.out.println("Enter the salary:");
	Double salary = sc.nextDouble();
	sc.nextLine();
	
	System.out.println("Enter the email:");
	String email = sc.nextLine();
	
	System.out.println("Enter the phone:");
	long phone = sc.nextLong();
	sc.nextLine();
	
	System.out.println("Enter the password:");
	String password = sc.nextLine();
	
	System.out.println("Enter the dob:");
	String dob = sc.nextLine();
	
	System.out.println("Enter the role:");
	String role = sc.nextLine();
	
	Employee emp = new Employee();
	emp.setName(name);
	emp.setSalary(salary);
	emp.setEmail(email);
	emp.setPhone(phone);
	emp.setPassword(password);
	emp.setDob(Date.valueOf(dob));
	emp.setRole(role);
	
	empList.add(emp);
	
	}
	return controller.addMultipleEmployee(empList);
}
public static void main(String[] args) {
	App app = new App();
	System.out.println("***************Welcome to Employee Management System****************");
	
	boolean loginCond = true;
	while(loginCond) {
		System.out.println("1. Admin Login");
		System.out.println("2. Employee Login");
		System.out.println("0. Enter your choice");
		int choice = app.sc.nextInt();
		app.sc.nextLine();
		
		switch (choice) {
		case 1:{
			//System.out.println("Admin Validate");
			if(app.adminValidate()) {
				System.out.println("Admin login successfully");
				
				boolean adminCond = true;
				while(adminCond) {
					System.out.println("1. Add Employee");
					System.out.println("2. Delete Employee");
					System.out.println("3. View Employee");
					System.out.println("4. View All Employee");
					System.out.println("5. Update Employee");
					System.out.println("6. Add Multiple Employee");
					
					System.out.println("0. Exit");
					
					int adminChoice = app.sc.nextInt();
					app.sc.nextLine();
					switch (adminChoice) {
					case 1:{
						try {
						if(app.addEmployee()) {
							System.out.println("Employee Added successfully");
						}
						else {
							System.out.println("Failed to add employee");
						}
						}
						catch(EmployeeAlreadyExistException e){
							System.out.println(e.getMessage());
						}
						break;
					}
					
					case 2:{
						// delete employee
						try {
						if(app.deleteEmployee()) {
							System.out.println("Employee deleted succesfully");
						}
						
						else {
							System.out.println("failed to delete employee");
						}
						}
						catch(NoRecordFoundException e) {
							System.out.println(e.getMessage());
						}
						break;
					}
					
					case 3:{
						// view employee
						try {
						Employee e = app.viewEmployee();
						if(e!=null) {
							System.out.println(e);
						}
						else {
							throw new NoRecordFoundException("employee with the id does not exist");
						}
						} catch(NoRecordFoundException e) {
							System.out.println(e.getMessage());
						}
						break;
					}
					
					case 4: {
					
						List<Employee> data =app.getAllEmployee();
						for(Employee e: data) {
							System.out.println(e);
						}
					}
					
					case 5:{
						try {
						if(app.updateEmployee()) {
							System.out.println("Employee update successfully");
						}
						else {
							System.out.println("failed to update");
						}
						}
						catch(NoRecordFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					
					case 6: {
						int data =app.addMultipleEmployee();
						System.out.println(data+"employees added");
						
					}
					
					case 0:{
						adminCond = false;
						break;
					}
						

					default:
						break;
					}
				}
			}
			else {
				System.out.println("Failed to login, invalid id and password");
			}
			break;
		}
		case 2:{
			System.out.println("Employee Validate");
			break;
		}

		case 0:{
			loginCond = false;
			System.out.println("Thank you for using the application");
			break;
		}

		default:
			break;
		}
	}
}
}
