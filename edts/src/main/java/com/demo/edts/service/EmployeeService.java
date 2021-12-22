package com.demo.edts.service;

import java.util.List;

import com.demo.edts.dto.EmployeeDTO;
import com.demo.edts.model.Employee;

public interface EmployeeService {

	List<EmployeeDTO> dtoAll();

	void addEmployee(Employee employee);

	void updateEmployee(Long employeeId, Employee employee);
}
