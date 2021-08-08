package com.play._base.classes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ReturnResult {

	private Boolean status;
	private String message;
	private Object data;
	private List<Object> dataList;
	
	public ReturnResult(Boolean status) {
		this.status = status;
	}
	
	public ReturnResult(Boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ReturnResult(Boolean status, String message, Object data) {
		this.status = status;
		this.message = message;
		
		if (data instanceof List) {
			this.dataList = (List<Object>) data;
		} else {
			this.data = data;
		}
	}
}
