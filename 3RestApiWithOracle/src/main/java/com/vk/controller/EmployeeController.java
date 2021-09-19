package com.vk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.entity.Address;
import com.vk.entity.Employee;
import com.vk.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listEmployee(){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", employeeService.getEmployees());
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody Employee employee){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", employeeService.saveEmployee(employee));
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/employees/object")
	public ResponseEntity<Map<String, Object>> listAddressFromObject(){
		Map<String, Object> resJson = new HashMap<>();
		try {				
			List<Object[]> employeesObject = employeeService.getEmployeesObject();
			for (Object[] objects : employeesObject) {
				System.out.println(objects[0]);
			}
			
			
			List<Object[]> employeesObjectSecond = employeeService.getEmployeesObjectSecond();
			for (Object[] objects2 : employeesObjectSecond) {
				System.out.println(objects2[0]+" "+objects2[1]);
			}
			
			resJson.put("dataObject", employeeService.getEmployeesObject());
			resJson.put("dataObjectSecond", employeeService.getEmployeesObjectSecond());
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/employees/names")
	public ResponseEntity<Map<String, Object>> listEmployeeNames(){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", employeeService.getEmployeeNames());
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
