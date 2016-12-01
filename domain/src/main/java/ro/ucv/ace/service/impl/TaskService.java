package ro.ucv.ace.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.IPathBuilder;
import ro.ucv.ace.builder.ITaskBuilder;
import ro.ucv.ace.dto.task.ESTaskDto;
import ro.ucv.ace.dto.task.TaskDto;
import ro.ucv.ace.exception.DuplicateEntryException;
import ro.ucv.ace.model.*;
import ro.ucv.ace.repository.ISubgroupRepository;
import ro.ucv.ace.repository.ITaskRepository;
import ro.ucv.ace.repository.impl.ProfessorRepository;
import ro.ucv.ace.repository.impl.TopicRepository;
import ro.ucv.ace.service.ITaskService;
import ro.ucv.ace.visitor.TaskVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzapt on 11/26/2016.
 */
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TaskVisitor taskVisitor;

    @Autowired
    private ITaskBuilder taskBuilder;

    @Autowired
    private ISubgroupRepository subgroupRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private IPathBuilder pathBuilder;


    @Override
    public TaskDto save(int professorId, int topicId, ESTaskDto taskDto) {

        // check if we can save the task
        List<Subgroup> subgroups = new ArrayList<>();
        Professor professor = professorRepository.findOne(professorId);
        Topic topic = topicRepository.findOne(topicId);

        if (professor.hasTaskWithNameInTopicWithId(taskDto.getName(), topicId)) {
            throw new DuplicateEntryException("Task: Duplicate entry '" + taskDto.getName() + "' for Professor with id " + professorId
                    + "and Topic with id" + topicId);
        }

        for (String subgroup : taskDto.getSubgroups()) {
            subgroups.add(subgroupRepository.findByName(subgroup));
        }

        Task task = null;
        if (taskDto.isTestsEnabled()) {
            task = taskBuilder.buildAutomaticTask(taskDto, subgroups, topic);
        } else {
            task = taskBuilder.buildManualTask(taskDto, subgroups, topic);
        }

        task = taskRepository.save(task);
        task.accept(taskVisitor);

        //create the folder for the task
        String stringPath = pathBuilder.buildTaskFolderPath(professor.getId(), topic.getId(), task.getId());
        Path path = Paths.get(stringPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // save the path into the task if it's automaticTask
        if (taskDto.isTestsEnabled()) {
            AutomaticTestedTask automaticTestedTask = (AutomaticTestedTask) task;
            automaticTestedTask.setTestFilesPath(stringPath);
        }

        // save the input and output file if available
        if (taskDto.isTestsEnabled()) {
            try {
                byte[] input = Base64.decodeBase64(taskDto.getInputFile());
                byte[] output = Base64.decodeBase64(taskDto.getOutputFile());
                try (OutputStream stream = new FileOutputStream(pathBuilder.buildPathForInputFile(stringPath))) {
                    stream.write(input);
                }
                try (OutputStream stream = new FileOutputStream(pathBuilder.buildPathForOutputFile(stringPath))) {
                    stream.write(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return taskVisitor.getTaskDto();
    }

    @Override
    public TaskDto delete(int taskId) {
        Task task = taskRepository.delete(taskId);
        task.accept(taskVisitor);

        // delete task folder
        String path = pathBuilder.buildTaskFolderPath(task.getTopic().getProfessor().getId(), task.getTopic().getId(), task.getId());
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskVisitor.getTaskDto();
    }
}
