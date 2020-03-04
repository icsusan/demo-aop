package com.aop.demoaop.service.impl;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.domain.util.StatusType;
import com.aop.demoaop.domain.util.TaskType;
import com.aop.demoaop.repository.TaskRepository;
import com.aop.demoaop.rest.dto.beans.TaskDto;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;
import com.aop.demoaop.rest.dto.request.UpdateTaskRequest;
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
		
		task.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		task.setCreatedBy(request.getOperator());
		
		task = taskRepository.save(task);
		
	}
	
	@Override
	public Optional<TaskDto> get(String id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return Optional.of(new TaskDto(task.get()));
		}
		return null;
	}

	@Override
	public TasksDto getAll() {
		return new TasksDto(taskRepository.findAll());
	}
	
	@Override
	public void update(UpdateTaskRequest request) {
		
		Optional<Task> taskOpt = taskRepository.findById(request.getId());
		if (taskOpt.isPresent()) {
			
			Task task = taskOpt.get();
			task.setName(request.getName());
			task.setAssignedTo(request.getResponsible());
			
			if (StringUtils.isBlank(request.getStatus())) {
				task.setStatus(StatusType.CREATED_STATUS.getType());
			} else {
				task.setStatus(request.getStatus());
			}
			
			task.setLastModifiedBy(request.getOperator());
			task.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			task = taskRepository.save(task);
		}
		
	}
	
	@Override
	public void delete(String id) {
		taskRepository.deleteById(id);
	}

}
