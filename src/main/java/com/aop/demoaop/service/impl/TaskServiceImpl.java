package com.aop.demoaop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.domain.util.StatusType;
import com.aop.demoaop.domain.util.TaskType;
import com.aop.demoaop.repository.TaskRepository;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;
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
			task.setType(TaskType.GENERIC_TASK.getType());
		} else {
			task.setType(request.getType());
		}
		
		task.setAssignedTo(request.getResponsible());
		
		if (StringUtils.isBlank(request.getStatus())) {
			task.setStatus(StatusType.CREATED_STATUS.getType());
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
	public TasksDto getAll() {
		List<Task> tasks = taskRepository.findAll();
		return new TasksDto(tasks);
	}
    
}
