package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException{
        service.deleteTask(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));

    }

    @RequestMapping(method = RequestMethod.GET, value = "findById")
    public Optional<Task> getTaskById(@PathVariable Long taskId){
        return service.getTask(taskId);
    }
}
