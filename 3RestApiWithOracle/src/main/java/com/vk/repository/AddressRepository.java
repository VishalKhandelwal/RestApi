package com.vk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vk.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	List<Address> findByEmployeeId(Long employeeId);
	
	@Query("From Address where employeeId = ?1")
	List<Address> findAddressByEmployeeId(Long employeeId);
	
	@Query(value = "Select * From Tbl_Address where employee_id = ?1", nativeQuery = true)
	List<Address> findAddressNativeQueryByEmployeeId(Long employeeId);
	
	@Query(value = "Select * From Tbl_Address where employee_id = :id", nativeQuery = true)
	List<Address> findAddressNativeQueryByEmployeeIdUsingParam(@Param("id") Long employeeId);
	
	@Query("From Address where id = :id")
	Object getAddressdetail(@Param("id") Long id);
	
	@Query(value = "Select * From Tbl_Address where employee_id = :id", nativeQuery = true)
	Object[][] getAddressdetailSecond(@Param("id") Long id);
}
