package com.bhuppi.taskmanager.controller;

import com.bhuppi.taskmanager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.bhuppi.taskmanager.model.Task;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173") // React runs here
public class TaskController {
    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Task> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Task add(@RequestBody Task t) {
        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}