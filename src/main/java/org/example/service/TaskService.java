package org.example.service;

import org.example.dto.TaskUpdateDto;
import org.example.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    private long nextId = 1;


    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        System.out.println("Created new task : "+task.getId());
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public Task updateTaskById(Long id, TaskUpdateDto updatedTask) {
        Task taskToBeEdited = getTaskById(id);
        if(taskToBeEdited == null) return null;
        taskToBeEdited.setTitle(updatedTask.getTitle());
        taskToBeEdited.setCompleted(updatedTask.isCompleted());
        return taskToBeEdited;
    }
}
