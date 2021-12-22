package com.demo.edts.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.edts.dto.EmployeeDTO;
import com.demo.edts.generic.EdtsEnum.GradeState;
import com.demo.edts.generic.ObjectManipulation;
import com.demo.edts.model.Employee;
import com.demo.edts.repository.EmployeeRepository;
import com.demo.edts.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {	
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeDTO> dtoAll() {
		logger.info("EmployeeServiceImpl dtoAll() start");
		
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> listDto = new ArrayList<EmployeeDTO>();

		for(Employee e : employees) {			
			EmployeeDTO origin = new EmployeeDTO();
			origin.setEmployeeId(e.getEmployeeId());
			origin.setName(e.getName());
			origin.setSalary(e.getSalary());
			origin.setGradeCode(e.getGrade().getGradeCode());
			
			BigDecimal bonusRate = BigDecimal.ZERO;
			
			if(GradeState.MANAGER.toString().equalsIgnoreCase(e.getGrade().getGradeStateName())) {
				bonusRate = e.getSalary().multiply(new BigDecimal(0.1));
			} else if (GradeState.SUPERVISOR.toString().equalsIgnoreCase(e.getGrade().getGradeStateName())) {
				bonusRate = e.getSalary().multiply(new BigDecimal(0.06));				
			} else {
				bonusRate = e.getSalary().multiply(new BigDecimal(0.03));	
			}
			
			
			BigDecimal totalBonus = e.getSalary().add(bonusRate);
			
			
			origin.setTotalBonus(totalBonus);
			listDto.add(origin);
			
		}
		
		
		return listDto;
	}	
	
	@Transactional
	@Override
	public void addEmployee(Employee employee) {
		logger.info("EmployeeServiceImpl addEmployee() start");		
		
		employeeRepository.save(employee);
	}

	@Transactional
	@Override
	public void updateEmployee(Long employeeId, Employee employee) {
		logger.info("EmployeeServiceImpl updateEmployee() start");
		Employee origin = (Employee) ObjectManipulation.evaluateOptional(employeeRepository.findById(employeeId));
		
		origin.setGrade(employee.getGrade());
		origin.setName(employee.getName());
		origin.setSalary(employee.getSalary());
		
		employeeRepository.save(origin);
	}
	
	private GradeState defineGrade(Employee employee) {

		logger.info("grade : {}", employee.getGrade().getGradeStateLevel());	
		
		GradeState x = GradeState.MANAGER;
		if(employee.getGrade().getGradeStateLevel() == 2) {
			x = GradeState.SUPERVISOR;
		} else if (employee.getGrade().getGradeStateLevel() == 1) {
			x = GradeState.STAFF;
		}
		
		return x;
	}
	
}
