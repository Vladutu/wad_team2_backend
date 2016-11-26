package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.Task;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.ITaskRepository;

/**
 * Created by tzapt on 11/26/2016.
 */
@Repository("taskRepository")
public class TaskRepository implements ITaskRepository {

    @Autowired
    private IJpaRepository<Task, Integer> innerTaskRepository;

    @Override
    public Task save(Task task) {
        return null;
    }
}
