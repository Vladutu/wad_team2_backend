package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.repository.ITaskRepository;
import ro.ucv.ace.service.ITaskService;

/**
 * Created by tzapt on 11/26/2016.
 */
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

}
