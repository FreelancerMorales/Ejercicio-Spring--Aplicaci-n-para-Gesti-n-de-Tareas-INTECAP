package com.proyecto1.proyecto_spring.service;

import org.springframework.stereotype.Service;

import com.proyecto1.proyecto_spring.model.Task;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void toggleTaskCompletion(Long id) {
        tasks.stream()
            .filter(task -> task.getId().equals(id))
            .forEach(task -> task.setCompleted(!task.isCompleted()));
    }    
}
