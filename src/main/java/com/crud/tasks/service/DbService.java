package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(long id) {
        return repository.findOne(id);
    }

}