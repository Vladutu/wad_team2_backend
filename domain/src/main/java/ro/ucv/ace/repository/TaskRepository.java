package ro.ucv.ace.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.AbstractTask;
import ro.ucv.ace.model.Task;

import java.util.List;

/**
 * Created by Geo on 28.10.2016.
 */
@Repository("taskRepository")
public class TaskRepository {

    @Autowired
    private JpaRepository<Task, AbstractTask, Integer> innerTaskRepository;

    public List<Task> findAll() {
        return innerTaskRepository.findAll();
    }

    public List<Task> findByName(String name) {
        return innerTaskRepository.findAllWhere(t -> t.getName().equals(name));
    }

    public Task findOne(Integer id) {
        return innerTaskRepository.findOne(id);
    }

    public Task save(Task t) {
        return innerTaskRepository.save(t);
    }

    public Task delete(Integer id) {
        return innerTaskRepository.delete(id);
    }
}
