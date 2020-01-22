package com.aop.demoaop.rest.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aop.demoaop.domain.persist.Task;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;
import com.aop.demoaop.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
    private final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> create(@RequestBody CreateTaskRequest request) {
        return () -> {
            taskService.save(request);
            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<ResponseEntity<Task>> get(@PathVariable("id") final String id) {
        return () -> taskService
                .get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<TasksDto> allTasks() {
        return () -> taskService.getAll();
    }
        
}
