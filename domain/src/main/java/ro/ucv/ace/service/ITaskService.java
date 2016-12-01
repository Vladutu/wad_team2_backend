package ro.ucv.ace.service;

import ro.ucv.ace.dto.task.ESTaskDto;
import ro.ucv.ace.dto.task.TaskDto;

/**
 * Created by tzapt on 11/26/2016.
 */
public interface ITaskService {

    TaskDto save(int professorId, int topicId, ESTaskDto taskDto);

    TaskDto delete(int taskId);
}
