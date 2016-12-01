package ro.ucv.ace.builder;

/**
 * Created by Geo on 01.12.2016.
 */
public interface IPathBuilder {

    String buildTaskFolderPath(int professorId, int topicId, int taskId);

    String buildPathForInputFile(String basePath);

    String buildPathForOutputFile(String basePath);

    String buildTopicFolderPath(Integer professorId, Integer topicId);

    String buildProfessorFolderPath(Integer professorId);
}
