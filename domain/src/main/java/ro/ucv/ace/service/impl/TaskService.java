package ro.ucv.ace.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.ITaskBuilder;
import ro.ucv.ace.dto.task.ESTaskDto;
import ro.ucv.ace.dto.task.TaskDto;
import ro.ucv.ace.exception.DuplicateEntryException;
import ro.ucv.ace.model.Professor;
import ro.ucv.ace.model.Subgroup;
import ro.ucv.ace.model.Task;
import ro.ucv.ace.model.Topic;
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

    @Value("${folder.root}")
    private String folderRoot;

    @Value("${folder.inp}")
    private String folderInput;

    @Value("${folder.outp}")
    private String folderOutput;

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

        //create the folder for the task
        String stringPath = String.format("%s%s%s%s%s%s%s", folderRoot, File.separator, professor.getPersonDetails().getSsn(),
                File.separator, topic.getName(), File.separator, taskDto.getName());
        Path path = Paths.get(stringPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // save the input and output file if available
        if (taskDto.isTestsEnabled()) {
            try {
                byte[] input = Base64.decodeBase64(taskDto.getInputFile());
                byte[] output = Base64.decodeBase64(taskDto.getOutputFile());
                try (OutputStream stream = new FileOutputStream(stringPath + File.separator + folderInput)) {
                    stream.write(input);
                }
                try (OutputStream stream = new FileOutputStream(stringPath + File.separator + folderOutput)) {
                    stream.write(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        Task task = null;
        if (taskDto.isTestsEnabled()) {
            task = taskBuilder.buildAutomaticTask(taskDto, subgroups, topic, stringPath);
        } else {
            task = taskBuilder.buildManualTask(taskDto, subgroups, topic);
        }

        task = taskRepository.save(task);
        task.accept(taskVisitor);

        return taskVisitor.getTaskDto();
    }
}
