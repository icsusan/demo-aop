package com.aop.demoaop.service;

import java.util.Optional;

import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;

public interface TaskService {

	public void save(CreateTaskRequest request);

    public Optional<Task> get(String id);
    
    public TasksDto getAll();

    public void delete(String id);

    public void deleteAll();
    
}
