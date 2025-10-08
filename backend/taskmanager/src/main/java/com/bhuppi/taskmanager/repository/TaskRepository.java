package com.bhuppi.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bhuppi.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}