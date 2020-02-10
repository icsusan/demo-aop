package com.aop.demoaop.rest.dto.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.aop.demoaop.domain.persist.Task;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasksDto implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3884575625069742689L;
	
	private List<TaskDto> tasks;
	
	public TasksDto(List<Task> tasks) {
		convertTasksToTasksDto(tasks);
	}
	
	public void convertTasksToTasksDto(List<Task> tasks) {
		for(Task task : tasks) {
			this.addTaskDto(new TaskDto(task));
		}
	}
	
	public void addTaskDto(TaskDto dto) {
		if (Objects.isNull(this.tasks)) {
			this.tasks = new ArrayList<>();
		}
		this.tasks.add(dto);
	}

}
