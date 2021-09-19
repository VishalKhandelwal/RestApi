package com.vk.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.entity.Employee;

@RestController
@RequestMapping("/api/different-ways")
public class DifferentWaysPostController {

	@PostMapping
	public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody Employee employee){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", employee);
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("/map")
	public ResponseEntity<Map<String, Object>> saveMapEmployee(@RequestBody Map<String,Object> resEmp){
		Map<String, Object> resJson = new HashMap<>();
		try {	
			String name = (String)resEmp.get("name");
			System.out.println(resEmp);
			resJson.put("data", name);
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listEmployee(@RequestParam("name")String name){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", name);
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Map<String, Object>> listPathEmployee(@PathVariable("name")String name){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", name);
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
