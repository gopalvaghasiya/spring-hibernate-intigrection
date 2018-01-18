package com.dineshonjava.service;

import java.util.List;





import com.dineshonjava.model.Employee;
import com.dineshonjava.model.Login;

/**
 * @author Gopal Vaghasiya
 *
 */
public interface EmployeeService {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployees();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
	
	public  List<Login> getLogin(int empid, int password);
	

	public List<Employee> getSearch(String empname);
}
