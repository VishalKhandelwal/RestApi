package com.vk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vk.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query(value = "select te.name,te.email,te.mobile_number,ta.country,ta.state,ta.city from tbl_employee te"
			     + " inner join tbl_address ta on te.id = ta.employee_id", nativeQuery = true)
	List<Object[]> getEmployeesObject();
	
	@Query("select e.email,a.city FROM Employee e inner join Address a on e.id = a.employeeId")
	List<Object[]> getEmployeesObjectSecond();
	
	@Query("SELECT name FROM Employee")
	List<String> getEmployeeNames();
}
