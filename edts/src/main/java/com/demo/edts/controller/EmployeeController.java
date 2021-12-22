package com.demo.edts.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.edts.dto.EmployeeDTO;
import com.demo.edts.generic.ObjectManipulation;
import com.demo.edts.generic.ViewFilter;
import com.demo.edts.model.Employee;
import com.demo.edts.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@Scope("session")
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all")
	public String dtoAll() throws JsonProcessingException {
		logger.info("EmployeeController | dtoAll start.");
		//return employeeService.dtoAll();
		
		List<EmployeeDTO> empList =  employeeService.dtoAll();

		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writerWithView(ViewFilter.Minimum.class).writeValueAsString(empList);
	}
	
	@PostMapping("/add")
	public void addEmployee(@RequestBody String body) {
		logger.info("EmployeeController | addEmployee starts...");
		
		try {

			body = redefineGrade(body);
			Employee submitted = null;
			submitted = (Employee) ObjectManipulation.instantiateClassFromName(Employee.class.getName(), body);
			employeeService.addEmployee(submitted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@PutMapping("/update/{employeeId}")
	public void updateEmployee( @PathVariable Long employeeId, @RequestBody String body) {
		logger.info("EmployeeController | updateEmployee , employeeId : {}", employeeId);
		
		try {
			body = redefineGrade(body);
			Employee submitted = null;
			submitted = (Employee) ObjectManipulation.instantiateClassFromName(Employee.class.getName(), body);
			employeeService.updateEmployee(employeeId, submitted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	private String redefineGrade(String body) throws JsonProcessingException {		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = mapper.readValue(body, Map.class);
		
		Integer grade = Integer.parseInt((String) map.get("grade")) - 1;
		map.remove("grade");
		map.put("grade", grade);
		
		body = mapper.writeValueAsString(map);
		
		return body;
	}

}
