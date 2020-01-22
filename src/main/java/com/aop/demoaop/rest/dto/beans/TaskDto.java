package com.aop.demoaop.rest.dto.beans;

import java.io.Serializable;

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
	private String createdDate;
	private String lastModifiedDate;

}
