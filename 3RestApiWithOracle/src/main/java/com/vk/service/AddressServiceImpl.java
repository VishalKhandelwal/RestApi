package com.vk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.entity.Address;
import com.vk.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address saveAddress(Address address) {			
		return addressRepository.saveAndFlush(address);
	}

	@Override
	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public List<Address> getAddressesByEmployeeIdOne(Long employeeId) {
		// TODO Auto-generated method stub
		return addressRepository.findByEmployeeId(employeeId);
	}

	@Override
	public List<Address> getAddressesByEmployeeIdTwo(Long employeeId) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressByEmployeeId(employeeId);
	}

	@Override
	public List<Address> getAddressesByEmployeeIdThree(Long employeeId) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressNativeQueryByEmployeeId(employeeId);
	}

	@Override
	public List<Address> getAddressesByEmployeeIdFour(Long employeeId) {
		// TODO Auto-generated method stub
		return addressRepository.findAddressNativeQueryByEmployeeIdUsingParam(employeeId);
	}

	@Override
	public Object getAddressByAddressId(Long addressId) {
		// TODO Auto-generated method stub
		return addressRepository.getAddressdetail(addressId);
	}

	@Override
	public Object[][] getAddressByAddressIdSecond(Long addressId) {
		// TODO Auto-generated method stub
		return addressRepository.getAddressdetailSecond(addressId);
	}

}
