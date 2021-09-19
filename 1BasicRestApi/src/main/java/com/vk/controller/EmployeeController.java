package com.vk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.entities.Employee;
import com.vk.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		Employee saveOrUpdateEmplyoee;
		try {
			saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);
			return saveOrUpdateEmplyoee;
		} catch (DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			System.out.println("Duplicate Email Id value insert...");
			return null;
		}
		
		
	}
	
	@GetMapping("/employee")
	public Employee getEmployeeByEmail(@RequestParam(value = "email",required = true) String email) {
		Optional<Employee> employee = employeeService.getEmployee(email);
		if(employee.isPresent()) {
			return employee.get();
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
	}
	
	@PutMapping("/{email}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("email") String email) {
		Optional<Employee> findByEmail = employeeService.getEmployee(email);
		if(findByEmail.isPresent()) {
			Employee saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);
			return saveOrUpdateEmplyoee;
		}
		return null;
	}
}
