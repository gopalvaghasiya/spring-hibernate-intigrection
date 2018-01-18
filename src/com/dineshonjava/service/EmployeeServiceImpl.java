package com.dineshonjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dineshonjava.dao.EmployeeDao;
import com.dineshonjava.model.Employee;
import com.dineshonjava.model.Login;

/***
 * 
 * @author Gopal Vaghasiya
 *
 */
@Service("employeeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}
	
	public List<Employee> listEmployees() {
		return employeeDao.listEmployees();
	}

	public Employee getEmployee(int empid) {
		return employeeDao.getEmployee(empid);
	}
	
	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);
	}

	@Override
	public List<Login> getLogin(int empid, int password) {
		return (List<Login>) employeeDao.getLogin(empid,password);
	}
	
	@Override
	public List<Employee> getSearch(String empname) {
		return (List<Employee>) employeeDao.getSearch(empname);
	}

}
