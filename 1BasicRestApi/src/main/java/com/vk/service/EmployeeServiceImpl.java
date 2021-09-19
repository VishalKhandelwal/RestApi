package com.vk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vk.entities.Employee;
import com.vk.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveOrUpdateEmplyoee(Employee employee) {
		Employee save = employeeRepository.save(employee);
		return save;
	}

	@Override
	public Optional<Employee> getEmployee(String email) {
		Optional<Employee> findByEmail = employeeRepository.findByEmail(email);
		return findByEmail;		
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

}
