package com.aop.demoaop.service;

import java.util.Optional;

import com.aop.demoaop.rest.dto.beans.TaskDto;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;
import com.aop.demoaop.rest.dto.request.UpdateTaskRequest;

public interface TaskService {

	public void save(CreateTaskRequest request);

    public Optional<TaskDto> get(String id);
    
    public TasksDto getAll();
    
    public void update(UpdateTaskRequest request);

    public void delete(String id);

    public void deleteAll();
    
}
