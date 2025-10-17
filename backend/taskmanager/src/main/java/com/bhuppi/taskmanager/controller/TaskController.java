package com.bhuppi.taskmanager.controller;

import com.bhuppi.taskmanager.model.Task;
import com.bhuppi.taskmanager.model.User;
import com.bhuppi.taskmanager.repository.TaskRepository;
import com.bhuppi.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskRepository repo;
    private final UserRepository userRepo;

    public TaskController(TaskRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Task> getAll(Authentication auth) {
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
        return repo.findByUser(user);
    }

    @PostMapping
    public Task add(@RequestBody Task task, Authentication auth) {
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
        task.setUser(user);
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication auth) {
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
        Task task = repo.findById(id).orElseThrow();
        if (task.getUser().getId().equals(user.getId())) {
            repo.delete(task);
        }
    }
}