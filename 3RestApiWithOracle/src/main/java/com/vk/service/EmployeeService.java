package com.vk.service;

import java.util.List;

import com.vk.entity.Employee;

public interface EmployeeService {
 
	public Employee saveEmployee(Employee employee);
	public List<Employee> getEmployees();
	public List<Object[]> getEmployeesObject();
	public List<Object[]> getEmployeesObjectSecond();	
	public List<String> getEmployeeNames();
}
