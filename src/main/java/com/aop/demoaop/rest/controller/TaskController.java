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

import com.aop.demoaop.rest.dto.beans.TaskDto;
import com.aop.demoaop.rest.dto.beans.TasksDto;
import com.aop.demoaop.rest.dto.request.CreateTaskRequest;
import com.aop.demoaop.rest.dto.request.UpdateTaskRequest;
import com.aop.demoaop.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
    private static final String PATH_ROOT = "/";
	private static final String PATH_ID = "/{id}";

	private static final String ID = "id";
    
	private final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }
    
    @RequestMapping(value = PATH_ROOT, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> create(@RequestBody CreateTaskRequest request) {
        return () -> {
            taskService.save(request);
            return ResponseEntity.accepted().build();
        };
    }

    @RequestMapping(value = PATH_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<ResponseEntity<TaskDto>> get(@PathVariable(ID) final String id) {
        return () -> taskService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @RequestMapping(value = PATH_ROOT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<TasksDto> getAllTasks() {
        return () -> taskService.getAll();
    }
      
    @RequestMapping(value = PATH_ROOT, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Void>> update(@RequestBody UpdateTaskRequest request) {
        return () -> {
            taskService.update(request);
            return ResponseEntity.accepted().build();
        };
    }
}
