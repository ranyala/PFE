package tn.com.st2i.project.tools.model;

import lombok.Data;

@Data
public class DaoObject {

	private String code;
	private Object objectReturn;
	private Long countTotal;
	
	public DaoObject(String code, Object objectReturn, Long countTotal) {
		this.code = code;
		this.objectReturn = objectReturn;
		this.countTotal = countTotal;
	}
	
}
