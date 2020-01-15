package com.aop.demoaop.rest.dto;

import java.io.Serializable;

import lombok.*;

/**
 * @author susan.inga
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskResponse implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7121793646667567510L;
	
	private String id;

}
