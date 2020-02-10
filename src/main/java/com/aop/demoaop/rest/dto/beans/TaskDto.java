package com.aop.demoaop.rest.dto.beans;

import java.io.Serializable;

import com.aop.demoaop.common.Constants;
import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.util.ConverterUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author susan.inga
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6354910503285694782L;
	
	private String id;
	private String name;
	private String type;
	private String assignedTo;
	private String status;
	private String createdBy;
	private String createdDate;
	private String lastModifiedBy;
	private String lastModifiedDate;
	
	public TaskDto(Task task) {
		this.id = task.getId();
		this.name = task.getName();
		this.type = task.getType();
		this.assignedTo = task.getAssignedTo();
		this.status = task.getStatus();
		this.createdBy = task.getCreatedBy();
		this.createdDate = ConverterUtil.convertTimestampToStringValueFormat(task.getCreatedDate(), Constants.LONG_FORMAT_DATE);
		this.lastModifiedBy = task.getLastModifiedBy();
		this.lastModifiedDate = ConverterUtil.convertTimestampToStringValueFormat(task.getLastModifiedDate(), Constants.LONG_FORMAT_DATE);
	}
	
}
