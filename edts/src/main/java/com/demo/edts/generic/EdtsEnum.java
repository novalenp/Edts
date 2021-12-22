package com.demo.edts.generic;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EdtsEnum {
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public enum GradeState {
	    MANAGER("1:Manager", 0), SUPERVISOR("2:Supervisor",1), STAFF("3:Staff",2);
		
		private String gradeCode;
		private String gradeStateName;
		private int gradeLevel;

		private GradeState(String gradeCode, int gradeLevel) {
			this.gradeCode = gradeCode;
			this.gradeStateName = this.name();
			this.gradeLevel = gradeLevel;
		}

		public String getGradeStateName() {
			return gradeStateName;
		}

		public String getGradeCode() {
			return gradeCode;
		}

		public int getGradeStateLevel() {
			return gradeLevel + 1;
		}
	}
}
