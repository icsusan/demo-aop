package com.aop.demoaop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.demoaop.domain.Task;
import com.aop.demoaop.repository.TaskRepository;
import com.aop.demoaop.rest.dto.CreateTaskRequest;
import com.aop.demoaop.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

	@Override
	public void save(CreateTaskRequest request) {
		
		Task task = new Task();
		task.setId(UUID.randomUUID().toString());
		task.setName(request.getName());

		if (StringUtils.isBlank(request.getType())) {
			task.setType("G");
		} else {
			task.setType(request.getType());
		}
		
		task.setAssignedTo(request.getResponsible());
		
		if (StringUtils.isBlank(request.getStatus())) {
			task.setStatus("C");
		} else {
			task.setStatus(request.getStatus());
		}
		
		task.setCreatedBy(request.getOperator());
		
		taskRepository.save(task);
		
	}

	@Override
	public Optional<Task> get(String id) {
		return taskRepository.findById(id);
	}

	@Override
	public void delete(String id) {
		taskRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		taskRepository.deleteAll();
	}

	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}
    
}
