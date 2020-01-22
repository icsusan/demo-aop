package com.aop.demoaop.domain.util;

import lombok.Getter;

/**
 * @author susan.inga
 *
 */

@Getter
public enum TaskType {

	GENERIC_TASK("G", "Generic"), 
	ANALYSIS_TASK("A", "Analysis"), 
	QC_TASK("Q", "QC"),
	DEVELOPMENT_TASK("D", "Development");

	private String type;
	private String description;

	private TaskType(String type, String description) {
		this.type = type;
		this.description = description;
	}

}
