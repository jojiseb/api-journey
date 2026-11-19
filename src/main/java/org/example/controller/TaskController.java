package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.TaskPatchDto;
import org.example.dto.TaskUpdateDto;
import org.example.model.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task manager",
      description = "Endpoints for creating, viewing, updating and deleting tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @Operation(summary = "Add a task", description = "Create a new task in memory with server generated id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks from memory")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task", description = "Retrieve a task from memory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid task id"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Remove a task from memory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task removed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task id"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
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
    @Operation(summary = "Fully update a task", description = "Fully replace a task in memory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task id"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Task updateById(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) { //Added Separate TaskUpdateDto with no id in requestBody
        return taskService.updateTaskById(id, taskUpdateDto); //to avoid possibility of having non-matching id in request than that of id in url
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update of a task", description = "Partially replace a task in memory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Task id"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Task patchUpdateById(@PathVariable Long id, @RequestBody TaskPatchDto taskPatchDto) {
        return taskService.patchUpdateTaskById(id, taskPatchDto);
    }


}
