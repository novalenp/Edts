package com.demo.edts.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.edts.generic.EdtsEnum.GradeState;
import com.demo.edts.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Long employeeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="salary")
	private BigDecimal salary;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="grade")
	@JsonView(ViewFilter.Minimalist.class)
	private GradeState grade;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public GradeState getGrade() {
		return grade;
	}

	public void setGrade(GradeState grade) {
		this.grade = grade;
	}
	
	
}
