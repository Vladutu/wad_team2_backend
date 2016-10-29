package ro.ucv.ace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.model.Task;
import ro.ucv.ace.repository.TaskRepository;

import java.util.List;

/**
 * Created by Geo on 28.10.2016.
 */
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByName(String name) {
        return taskRepository.findByName(name);
    }

    public Task findOne(Integer id) {
        return taskRepository.findOne(id);
    }

    public Task save(Task t) {
        return taskRepository.save(t);
    }

    public Task delete(Integer id) {
        return taskRepository.delete(id);
    }
}
