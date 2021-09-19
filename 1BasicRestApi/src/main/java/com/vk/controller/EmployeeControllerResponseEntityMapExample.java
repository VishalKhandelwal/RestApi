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
@RequestMapping("/api/v4/employees")
public class EmployeeControllerResponseEntityMapExample {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getEmployees(){
		
		Map<String, Object> resJson = new HashMap<>();
		try {
		List<Employee> employees = employeeService.getEmployees();
		resJson.put("data", employees);
		resJson.put("status", 200);	
		return new ResponseEntity<>(resJson,HttpStatus.OK);
		}
		catch(Exception e) {
			resJson.put("message", "Internal Server Error");	
			resJson.put("status", 500);		
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody Employee employee) {
		Map<String, Object> resJson = new HashMap<>();
		
		try {
			Employee saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);
			resJson.put("data", saveOrUpdateEmplyoee);
			resJson.put("status", 201);	
			return new ResponseEntity<>(resJson,HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			resJson.put("message", "Duplicate Email Id value insert...");
			resJson.put("status", 409);	
			return new ResponseEntity<>(resJson,HttpStatus.CONFLICT);
		}
		catch(Exception e) {
			resJson.put("message", "Internal Server Error");		
			resJson.put("status", 500);		
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/employee")
	public ResponseEntity<Map<String, Object>> getEmployeeByEmail(@RequestParam(value = "email",required = true) String email) {
		Map<String, Object> resJson = new HashMap<>();
		try {
		Optional<Employee> employee = employeeService.getEmployee(email);
		if(employee.isPresent()) {			 
			resJson.put("data", employee.get());
			resJson.put("status", 200);	
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		}
		else {
			resJson.put("message", "Employee Not Found..");
			resJson.put("status", 404);
			return new ResponseEntity<>(resJson,HttpStatus.NOT_FOUND);
		}
		}
		catch(Exception e) {
			resJson.put("message", "Internal Server Error");		
			resJson.put("status", 500);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable("id") Long id) {
		Map<String, Object> resJson = new HashMap<>();
		try {
		employeeService.deleteEmployee(id);
		resJson.put("message", "Employee Deleted Successfully..");
		resJson.put("status", 204);
		return new ResponseEntity<>(resJson,HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			resJson.put("message", "Internal Server Error");		
			resJson.put("status", 500);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee employee,@PathVariable("email") String email) {
		Map<String, Object> resJson = new HashMap<>();
		try {
		Optional<Employee> findByEmail = employeeService.getEmployee(email);
		if(findByEmail.isPresent()) {
			Employee saveOrUpdateEmplyoee = employeeService.saveOrUpdateEmplyoee(employee);			
			resJson.put("data", saveOrUpdateEmplyoee);
			resJson.put("status", 200);
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		}
		else {
			resJson.put("message", "Employee Not Found..");
			resJson.put("status", 404);
			return new ResponseEntity<>(resJson,HttpStatus.NOT_FOUND);
		}
		
		}
		catch(Exception e) {
			resJson.put("message", "Internal Server Error");		
			resJson.put("status", 500);		
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
