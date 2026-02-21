package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.entities.Employee;

//Dao(Data access object)- data access object here database logic

public class EmployeeDao {
	
	FileInputStream fis;
	Properties property;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement stm;
public EmployeeDao() {
	try {
		fis = new FileInputStream("C:\\Users\\ADMIN\\eclipse-workspace\\employee_management_system\\src\\main\\resources\\config.properties");
	
	property = new Properties();
	property.load(fis);
	
	//Load the Driver
	String driverPath = property.getProperty("driver");
	Class.forName(driverPath);
   System.out.println("Driver Loaded");
   
   String url = property.getProperty("url");
   con = DriverManager.getConnection(url, property);
   System.out.println("Connection Done");
   
   stm = con.createStatement();
	}
	
	
	
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public boolean adminValidate(Employee emp) {
	String query = "select password, role from employee where id = ?";
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, emp.getId());
		
		rs = ps.executeQuery();
		if(rs.next()) {
			if(rs.getString("password").equals(emp.getPassword())&& rs.getString("role").equalsIgnoreCase("admin")) {
				return true;
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
}


public boolean addEmployee(Employee emp) {
	String insert = "insert into employee(name, salary, email, phone,password, dob, role) values(?,?,?,?,?,?,?)";
	try {
		ps = con.prepareStatement(insert);
		
		ps.setString(1, emp.getName());
		ps.setDouble(2, emp.getSalary());
		ps.setString(3, emp.getEmail());
		ps.setLong(4, emp.getPhone());
		ps.setString(5, emp.getPassword());
		ps.setDate(6, emp.getDob());
		ps.setString(7, emp.getRole());
		
		if(ps.executeUpdate() > 0) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
	
}

public boolean deleteEmployee(int id) {
	String delete = "Delete from employee where id = ?";
	try {
		ps = con.prepareStatement(delete);
		ps.setInt(1, id);
		
		if(ps.executeUpdate()>0) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
	
}

public Employee viewEmployee(int id) {
	String view = "Select * from employee where id =? ";
	try {
		ps = con.prepareStatement(view);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			return new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"), rs.getString("email"), rs.getLong("phone"), rs.getString("password"),rs.getDate("dob"), rs.getString("role"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

// to check if the employee already exist or not if exist we will not insert the data
public boolean checkIfEmployeeExists(String email, Long phone) {
	String query = "select * from employee where email=? or phone=?";
	
	try {
		ps = con.prepareStatement(query);
		ps.setString(1, email);
		ps.setLong(2, phone);
		
		rs = ps.executeQuery();
		if(rs.next()) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}


public List<Employee> getAllEmployee(){
	List<Employee> data = new ArrayList<Employee>();
	String query = "Select * from employee where role != 'admin' ";
	try {
		rs = stm.executeQuery(query);
		
		if(rs.next()) {
			do {
				data.add (new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"), rs.getString("email"), rs.getLong("phone"), rs.getString("password"),rs.getDate("dob"), rs.getString("role")));
				
			}while(rs.next());
			return data;
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

public boolean updateEmployee(Employee emp) {
	String query = "update employee set name=?, salary =?, email=?, phone =?, password =?, dob=?, role=? where id=?";
	try {
		ps = con.prepareStatement(query);
		ps.setString(1, emp.getName());
		ps.setDouble(2, emp.getSalary());
		ps.setString(3, emp.getEmail());
		ps.setLong(4, emp.getPhone());
		ps.setString(5, emp.getPassword());
		ps.setDate(6, emp.getDob());
		ps.setString(7, emp.getRole());
		ps.setInt(8, emp.getId());
		
		if(ps.executeUpdate()>0) {
			return true;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public int addMultipleEmployee(List<Employee> empList) {
	String query = "insert into employee(name, salary, email, phone,password, dob, role) values(?,?,?,?,?,?,?)";
	int resultCount = 0;
	try {
		ps = con.prepareStatement(query);
		
		for(Employee emp: empList) {
		ps.setString(1,emp.getName() );
		ps.setDouble(2, emp.getSalary());
		ps.setString(3, emp.getEmail());
		ps.setLong(4, emp.getPhone());
		ps.setString(5, emp.getPassword());
		ps.setDate(6, emp.getDob());
		ps.setString(7, emp.getRole());
		
		ps.addBatch();
		}
		
		int[] result = ps.executeBatch();
		for(int r: result) {
			if(r>0) {
			resultCount++;
			}
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return resultCount;
}

//public static void main(String[] args) {
//	EmployeeDao emp = new EmployeeDao();
//			
//}
}
