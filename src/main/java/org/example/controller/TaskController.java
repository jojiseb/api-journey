package org.example.controller;

import org.example.dto.TaskUpdateDto;
import org.example.model.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteById(id);
        if(isDeleted) {
            return "Task with id : "+id+" is deleted";
        }
        else {
            return "No task found for id "+id;
        }
    }

    @PutMapping("/{id}")
    public Task updateById(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) { //Added Separate TaskUpdateDto with no id in requestBody
        return taskService.updateTaskById(id, taskUpdateDto); //to avoid possibility of having non-matching id in request than that of id in url
    }


}
