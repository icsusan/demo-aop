package com.aop.demoaop.rest.dto;

import java.util.List;

import com.aop.demoaop.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksDTO {
	
    private List<Task> tasks;

}
