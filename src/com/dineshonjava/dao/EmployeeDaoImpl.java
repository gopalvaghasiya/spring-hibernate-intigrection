package com.dineshonjava.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dineshonjava.model.Employee;
import com.dineshonjava.model.Login;

/**
 * @author Gopal Vaghasiya
 *
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployees() {
		return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
	}

	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
	}

	public void deleteEmployee(Employee employee) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee WHERE empid = "+employee.getEmpId()).executeUpdate();
	}

	
	public List<Login> getLogin(int empid , int password) {
		return (List<Login>) sessionFactory.getCurrentSession().createSQLQuery("select empid from ragister where empid="+empid+" and password="+password+" ").list();
	}
	
	public List<Employee> getSearch(String empname) {
		System.out.println(empname+"<---");
		return (List<Employee>) sessionFactory.getCurrentSession().createSQLQuery("select * from employee where empname='"+empname+"' ").list();
	}

	

	

	

}
