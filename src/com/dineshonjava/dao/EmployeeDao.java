package com.dineshonjava.dao;

import java.util.List;




import com.dineshonjava.model.Employee;
import com.dineshonjava.model.Login;

/**
 * @author Gopal Vaghasiya
 *
 */
public interface EmployeeDao {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployees();
	
	public Employee getEmployee(int employeeid);
	
	public void deleteEmployee(Employee employee);

	public  List<Login> getLogin(int empid, int password);
	
	public  List<Employee> getSearch(String empname);
}
