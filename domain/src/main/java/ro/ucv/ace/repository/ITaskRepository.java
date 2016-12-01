package ro.ucv.ace.repository;

import ro.ucv.ace.model.Task;

/**
 * Created by tzapt on 11/26/2016.
 */
public interface ITaskRepository {

    Task save(Task task);

    Task delete(int taskId);
}
