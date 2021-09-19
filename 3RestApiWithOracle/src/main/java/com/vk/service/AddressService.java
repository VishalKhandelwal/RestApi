package com.vk.service;

import java.util.List;

import com.vk.entity.Address;

public interface AddressService {
 
	public Address saveAddress(Address address);
	public List<Address> getAddresses();
	public List<Address> getAddressesByEmployeeIdOne(Long employeeId);
	public List<Address> getAddressesByEmployeeIdTwo(Long employeeId);
	public List<Address> getAddressesByEmployeeIdThree(Long employeeId);
	public List<Address> getAddressesByEmployeeIdFour(Long employeeId);
	public Object getAddressByAddressId(Long addressId);
	public Object[][] getAddressByAddressIdSecond(Long addressId);
}
