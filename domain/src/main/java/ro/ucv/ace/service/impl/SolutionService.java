package ro.ucv.ace.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.IPathBuilder;
import ro.ucv.ace.builder.ISolutionBuilder;
import ro.ucv.ace.dto.ResponseMessageDto;
import ro.ucv.ace.dto.solution.ESSolutionDto;
import ro.ucv.ace.dto.solution.SolutionDto;
import ro.ucv.ace.exception.DeadlinePassedException;
import ro.ucv.ace.exception.EntityNotFoundException;
import ro.ucv.ace.model.*;
import ro.ucv.ace.repository.ISolutionRepository;
import ro.ucv.ace.repository.IStudentRepository;
import ro.ucv.ace.repository.ITaskRepository;
import ro.ucv.ace.service.ISolutionService;
import ro.ucv.ace.utility.Unzipper;
import ro.ucv.ace.visitor.SolutionVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by tzapt on 12/4/2016.
 */
@Service("solutionService")
@Transactional(rollbackFor = Exception.class)
public class SolutionService implements ISolutionService {

    @Autowired
    private ISolutionRepository solutionRepository;

    @Autowired
    private SolutionVisitor solutionVisitor;

    @Autowired
    private ISolutionBuilder solutionBuilder;

    @Autowired
    private IPathBuilder pathBuilder;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private Unzipper unzipper;

    @Override
    public ResponseMessageDto save(int studentId, int taskId, ESSolutionDto solutionDto) {
        Student student = studentRepository.findOne(studentId);
        Task task = taskRepository.findOne(taskId);
        Professor professor = task.getTopic().getProfessor();
        Topic topic = task.getTopic();

        ResponseMessageDto messageDto;

        Date date = new Date();
        if (task.getDeadline().isBefore(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            throw new DeadlinePassedException("Deadline for " + task.getName() + " has passed!");
        }

        String stringPath = pathBuilder.buildStudentSolutionFolderPath(professor.getId(), topic.getId(), taskId, studentId);

        Path path = Paths.get(stringPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);

            } else {
                Files.delete(path);
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] zipFile = Base64.decodeBase64(solutionDto.getInputFile());
        String zipPath = stringPath + File.separator + "test.zip";

        //Saves zip, extracts it and then deletes it
        try {
            FileUtils.writeByteArrayToFile(new File(zipPath), zipFile);
            unzipper.unzip(zipPath, stringPath);
            Path zip = Paths.get(zipPath);
            Files.delete(zip);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Solution solution = task.getSolutionByStudent(studentId);
            task.removeSolution(solution);
            taskRepository.save(task);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }

        Solution solution = solutionBuilder.build(student, task, stringPath);

        messageDto = task.addSolution(solution);
        taskRepository.save(task);
        solutionRepository.save(solution);

        return messageDto;
    }

    @Override
    public SolutionDto delete(int solutionId, ESSolutionDto solutionDto) {
        Solution solution = solutionRepository.delete(solutionId);
        solution.accept(solutionVisitor);

        try {
            FileUtils.deleteDirectory(new File(solution.getDirectoryPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solutionVisitor.getSolutionDto();

    }
}
