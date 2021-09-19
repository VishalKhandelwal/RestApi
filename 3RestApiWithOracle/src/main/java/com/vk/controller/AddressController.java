package com.vk.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
import com.vk.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listAddress(){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", addressService.getAddresses());
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveAddress(@RequestBody Address address){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("data", addressService.saveAddress(address));
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<Map<String, Object>> listAddressByEmployeeId(){
		Map<String, Object> resJson = new HashMap<>();
		try {			
			resJson.put("dataOne", addressService.getAddressesByEmployeeIdOne(1L));
			resJson.put("dataTwo", addressService.getAddressesByEmployeeIdTwo(1L));
			resJson.put("dataThree", addressService.getAddressesByEmployeeIdThree(1L));
			resJson.put("dataFour", addressService.getAddressesByEmployeeIdFour(1L));
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/employees/object")
	public ResponseEntity<Map<String, Object>> listAddressFromObject(){
		Map<String, Object> resJson = new HashMap<>();
		try {	
			Object addressObject = addressService.getAddressByAddressId(1L);			
			Address adr = (Address) addressObject;
			
			//System.out.println(country);
			System.out.println(adr);
			
			Object[][] addressByAddressIdSecond = addressService.getAddressByAddressIdSecond(1L);
			System.out.println(addressByAddressIdSecond[0][2]);
			
			resJson.put("dataObject", addressService.getAddressByAddressId(1L));
			resJson.put("dataObjectSecond", addressService.getAddressByAddressIdSecond(1L));
			resJson.put("status", "200");
			return new ResponseEntity<>(resJson,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resJson.put("status", "500");
			return new ResponseEntity<>(resJson,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
