package com.aop.demoaop.service;

import java.util.List;
import java.util.Optional;

import com.aop.demoaop.domain.Task;
import com.aop.demoaop.rest.dto.CreateTaskRequest;

public interface TaskService {

	public void save(CreateTaskRequest request);

    public Optional<Task> get(String id);
    
    public List<Task> getAll();

    public void delete(String id);

    public void deleteAll();
    
}
