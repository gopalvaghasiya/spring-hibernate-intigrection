package com.dineshonjava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Gopal Vaghasiya
 *
 */
@Entity
@Table(name="Login")
public class Login implements Serializable{

private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "empid")
	private Integer empId;
	
	@Column(name="password")
	private Integer password;
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	
}