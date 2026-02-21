package model.entities;

import java.sql.Date;

//entity - class present inside model
public class Employee {
private Integer id;
private String name;
private Double salary;
private String email;
private Long phone;
private String password;
private Date dob;
private String role;

public Employee() {
	
}

public Employee(Integer id, String name, Double salary, String email, Long phone, String password, Date dob,
		String role) {
	this(name,salary,email,phone,password,dob,role);
	this.id = id;
}

public Employee(String name, Double salary, String email, Long phone, String password, Date dob, String role) {
	super();
	this.name = name;
	this.salary = salary;
	this.email = email;
	this.phone = phone;
	this.password = password;
	this.dob = dob;
	this.role = role;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Double getSalary() {
	return salary;
}

public void setSalary(Double salary) {
	this.salary = salary;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getPhone() {
	return phone;
}

public void setPhone(Long phone) {
	this.phone = phone;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", email=" + email + ", phone=" + phone
			+ ", password=" + password + ", dob=" + dob + ", role=" + role + "]";
}




}