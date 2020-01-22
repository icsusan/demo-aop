package com.aop.demoaop.domain.persist;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author susan.inga
 *
 */

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4373077252462008906L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "assignedto")
	private String assignedTo;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "lastmodifiedby")
	private String lastModifiedBy;
	
	@Column(name = "lastmodifieddate")
	private Timestamp lastModifiedDate;

}
