package com.vk.service;

import java.util.List;
import java.util.Optional;

import com.vk.entities.Employee;

public interface EmployeeService {

	public Employee saveOrUpdateEmplyoee(Employee employee);
	public Optional<Employee> getEmployee(String email);
	public List<Employee> getEmployees();
	public void deleteEmployee(Long id);	
}
