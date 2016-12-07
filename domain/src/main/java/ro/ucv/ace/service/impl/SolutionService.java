package ro.ucv.ace.service.impl;

import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.builder.IPathBuilder;
import ro.ucv.ace.builder.ISolutionBuilder;
import ro.ucv.ace.dto.ResponseMessageDto;
import ro.ucv.ace.dto.solution.ESSolutionDto;
import ro.ucv.ace.exception.DeadlinePassedException;
import ro.ucv.ace.exception.EntityNotFoundException;
import ro.ucv.ace.model.*;
import ro.ucv.ace.repository.ISolutionRepository;
import ro.ucv.ace.repository.IStudentRepository;
import ro.ucv.ace.repository.ITaskRepository;
import ro.ucv.ace.service.ISolutionService;
import ro.ucv.ace.utility.impl.IUnzipper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Created by tzapt on 12/4/2016.
 */
@Service("solutionService")
@Transactional(rollbackFor = Exception.class)
public class SolutionService implements ISolutionService {

    @Autowired
    private ISolutionRepository solutionRepository;

    @Autowired
    private ISolutionBuilder solutionBuilder;

    @Autowired
    private IPathBuilder pathBuilder;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUnzipper unzipper;

    @Override
    public ResponseMessageDto save(int studentId, int taskId, ESSolutionDto solutionDto) {
        Student student = studentRepository.findOne(studentId);
        Task task = taskRepository.findOne(taskId);
        Professor professor = task.getTopic().getProfessor();
        Topic topic = task.getTopic();

        ResponseMessageDto messageDto;

        LocalDate currentDate = LocalDate.now();
        if (task.getDeadline().isBefore(currentDate)) {
            throw new DeadlinePassedException("You can no longer submit solution for this task.Deadline has passed.");
        }

        String stringPath = pathBuilder.buildAbsoluteStudentSolutionFolderPath(professor.getId(), topic.getId(), taskId, studentId);

        Path path = Paths.get(stringPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);

            } else {
                FileUtils.deleteDirectory(new File(stringPath));
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] zipFile = Base64.decodeBase64(solutionDto.getInputFile());
        String zipPath = stringPath + File.separator + "test.zip";

        //Saves zip, extracts it and then deletes it
        try {
            try (OutputStream stream = new FileOutputStream(zipPath)) {
                stream.write(zipFile);
            }
            unzipper.unzip(zipPath, stringPath);
            Path zip = Paths.get(zipPath);
            Files.delete(zip);
        } catch (IOException | ZipException e) {
            e.printStackTrace();
        }


        try {
            Solution solution = task.getSolutionByStudent(studentId);
            solutionRepository.delete(solution.getId());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }

        Solution solution = solutionBuilder.build(student, task,
                pathBuilder.buildRelativeStudentSolutionFolderPath(professor.getId(), topic.getId(), taskId, studentId));

        messageDto = task.addSolution(solution);
        taskRepository.save(task);
        solutionRepository.save(solution);

        return messageDto;
    }
}
