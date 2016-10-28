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
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(Task task) {
        taskRepository.saveTask(task);
    }

    public List<Task> findTasks() {
        return taskRepository.findTasks();
    }

    public void updateTask() {
        List<Task> tasks = findTasks();
        Task t = tasks.get(0);
        t.setName("updatedName");
    }
}
