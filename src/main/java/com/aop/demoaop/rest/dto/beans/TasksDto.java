package com.aop.demoaop.rest.dto.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.util.ConverterUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasksDto implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3884575625069742689L;
	
	private List<TaskDto> tasks;
	
	public TasksDto(List<Task> tasks) {
		convertTasksToTasksDto(tasks);
	}
	
	public void convertTasksToTasksDto(List<Task> tasks) {
		
		String json = null;
		TaskDto dto = null;
		
		try {
			
			for(Task task : tasks) {
				json = ConverterUtil.convertObjectToJson(task);
				dto = ConverterUtil.convertirJsonToClass(json, TaskDto.class);
				addTaskDto(dto);
			}
		
		} catch (Exception e) {
        	log.info("Exception : ", e);
		}
	}
	
	public void addTaskDto(TaskDto dto) {
		
		if (Objects.isNull(this.tasks)) {
			this.tasks = new ArrayList<>();
		}
		
		this.tasks.add(dto);
	}

}
