package com.demo.edts.dto;

import java.math.BigDecimal;

import com.demo.edts.model.Employee;

public class EmployeeDTO extends Employee {
	
	private String gradeCode;
	private BigDecimal totalBonus;

	public BigDecimal getTotalBonus() {
		return totalBonus;
	}

	public void setTotalBonus(BigDecimal totalBonus) {
		this.totalBonus = totalBonus;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	
}
