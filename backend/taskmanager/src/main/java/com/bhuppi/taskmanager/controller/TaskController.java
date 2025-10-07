// src/main/java/com/bhuppi/taskmanager/controller/TaskController.java
package com.bhuppi.taskmanager.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.bhuppi.taskmanager.model.Task;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173") // React runs here
public class TaskController {

    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Learn Spring Boot"),
            new Task(2, "Build Task App")
    ));

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(tasks.size() + 1);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        tasks.removeIf(t -> t.getId() == id);
    }
}