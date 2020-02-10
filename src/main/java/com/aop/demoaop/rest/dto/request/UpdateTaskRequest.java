package com.aop.demoaop.rest.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author susan.inga
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskRequest {

	@NotBlank(message = "Id is a required field.")
	@Size(min = 1, max = 50, message = "Id must have between 1 and 50 characters.")
	private String id;
	
	@NotBlank(message = "Name is a required field.")
	@Size(min = 1, max = 50, message = "Name must have between 1 and 50 characters.")
	private String name;
	
	@Size(min = 1, max = 20, message = "Responsible must have between 1 and 20 characters.")
	private String responsible;
	
	@Pattern(regexp = "[C|O|S|F]{1}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Allow values are C (Created), O (Ongoing), S (Stopped) or F (Finished)")
	private String status;
	
	@NotBlank(message = "Operator is a required field.")
	@Size(min = 1, max = 20, message = "User must have between 1 and 10 characters.")
	private String operator;
	
}
