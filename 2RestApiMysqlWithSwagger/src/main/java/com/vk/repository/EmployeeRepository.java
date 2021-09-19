package com.vk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
}
