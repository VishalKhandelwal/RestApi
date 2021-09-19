package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.entity.Employee;
import com.vk.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {			
		return employeeRepository.saveAndFlush(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Object[]> getEmployeesObject() {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeesObject();
	}

	@Override
	public List<Object[]> getEmployeesObjectSecond() {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeesObjectSecond();
	}

	@Override
	public List<String> getEmployeeNames() {
		// TODO Auto-generated method stub
		return employeeRepository.getEmployeeNames();
	}

}
