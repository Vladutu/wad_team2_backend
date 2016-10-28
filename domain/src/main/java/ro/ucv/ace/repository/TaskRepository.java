package ro.ucv.ace.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.config.JinqSource;
import ro.ucv.ace.model.AbstractTask;
import ro.ucv.ace.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geo on 28.10.2016.
 */
@Repository("taskRepository")
public class TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JinqSource jinqSource;

    public void saveTask(Task task) {
        entityManager.merge(task);
    }

    public List<Task> findTasks() {
        List<AbstractTask> abstractTasks = entityManager.createQuery("SELECT t FROM AbstractTask t", AbstractTask.class).getResultList();

        List<Task> tasks = new ArrayList<>();
        abstractTasks.forEach(tasks::add);

        return tasks;
    }

    public List<Task> findTaskByName(String name) {
        return jinqSource.streamAll(entityManager, AbstractTask.class)
                .where(t -> t.getName().equals(name))
                .collect(Collectors.toList());
    }
}
