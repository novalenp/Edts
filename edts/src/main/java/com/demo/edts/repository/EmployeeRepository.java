package com.demo.edts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.edts.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
