package com.proyecto1.proyecto_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyecto1.proyecto_spring.model.Task;
import com.proyecto1.proyecto_spring.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/api")
    @ResponseBody
    public List<Task> getAllTasksApi() {
        return taskService.getAllTasks();
    }

    @PostMapping("/api")
    @ResponseBody
    public Task createTaskApi(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteTaskApi(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "new-task";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTaskCompletion(@PathVariable Long id) {
        taskService.toggleTaskCompletion(id);
        return "redirect:/tasks";
    }
}