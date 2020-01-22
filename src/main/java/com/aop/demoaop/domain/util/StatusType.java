package com.aop.demoaop.domain.util;

import lombok.Getter;

/**
 * @author susan.inga
 *
 */

@Getter
public enum StatusType {

	CREATED_STATUS("C", "Created"), 
	ONGOING_STATUS("O", "Ongoing"), 
	STOPEED_STATUS("S", "Stopped"),
	FINISHED_STATUS("F", "Finished");

	private String type;
	private String description;

	private StatusType(String type, String description) {
		this.type = type;
		this.description = description;
	}

}
