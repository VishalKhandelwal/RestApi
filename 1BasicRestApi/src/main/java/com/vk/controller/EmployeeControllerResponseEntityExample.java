package com.vk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v3/employees")
public class EmployeeControllerResponseEntityExample {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {

		try {
			List<Employee> employees = employeeService.getEmployees();
			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		try {
			Employee saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);
			return new ResponseEntity<>(saveOrUpdateEmplyoee, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {			
			 return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		} catch (Exception e) {
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@GetMapping("/employee")
	public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam(value = "email", required = true) String email) {		
		try {
			Optional<Employee> employee = employeeService.getEmployee(email);
			if (employee.isPresent()) {
				return new ResponseEntity<>(employee.get(), HttpStatus.OK);
			} else {
				 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {		
		try {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PutMapping("/{email}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("email") String email) {		
		try {
			Optional<Employee> findByEmail = employeeService.getEmployee(email);
			if (findByEmail.isPresent()) {
				Employee saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);
				return new ResponseEntity<>(saveOrUpdateEmplyoee, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
