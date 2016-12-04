package ro.ucv.ace.builder.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.ucv.ace.builder.IPathBuilder;

import java.io.File;

/**
 * Created by Geo on 01.12.2016.
 */
@Component
public class PathBuilder implements IPathBuilder {

    @Value("${folder.root}")
    private String folderRoot;

    @Value("${folder.inp}")
    private String folderInput;

    @Value("${folder.outp}")
    private String folderOutput;

    @Override
    public String buildTaskFolderPath(int professorId, int topicId, int taskId) {
        return String.format("%s%s%s%s%s%s%s", folderRoot, File.separator, professorId,
                File.separator, topicId, File.separator, taskId);
    }

    @Override
    public String buildPathForInputFile(String basePath) {
        return basePath + File.separator + folderInput;
    }

    @Override
    public String buildPathForOutputFile(String basePath) {
        return basePath + File.separator + folderOutput;
    }

    @Override
    public String buildTopicFolderPath(Integer professorId, Integer topicId) {
        return String.format("%s%s%s%s%s", folderRoot, File.separator, professorId,
                File.separator, topicId);
    }

    @Override
    public String buildProfessorFolderPath(Integer professorId) {
        return String.format("%s%s%s", folderRoot, File.separator, professorId);
    }

    @Override
    public String buildStudentSolutionFolderPath(int professorId, int topicId, int taskId, int studentId) {
        return String.format("%s%s%s%s%s%s%s%s%s", folderRoot, File.separator, professorId, File.separator, topicId, File.separator,
                taskId, File.separator, studentId);
    }
}
