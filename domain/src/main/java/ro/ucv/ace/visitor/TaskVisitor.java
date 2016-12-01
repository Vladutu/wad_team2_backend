package ro.ucv.ace.visitor;

import org.springframework.stereotype.Component;
import ro.ucv.ace.dto.task.TaskDto;
import ro.ucv.ace.model.Subgroup;
import ro.ucv.ace.model.Task;

/**
 * Created by Geo on 30.11.2016.
 */
@Component
public class TaskVisitor {

    private TaskDto taskDto;


    public void visit(Task task) {
        boolean testsEnabled = task.hasTestsEnabled();
        boolean plagiarismAnalyserEnabled = task.hasPlagiarismAnalyserEnabled();

        int sentSolutions = task.getSolutions().size();
        int noStudents = 0;
        for (Subgroup subgroup : task.getSubgroups()) {
            noStudents += subgroup.getStudents().size();
        }

        taskDto = new TaskDto(task.getId(), task.getName(), task.getDescription(), task.getDeadline().toString(), task.getLanguage().toString()
                , task.isCanSubmitSolutions(), testsEnabled, plagiarismAnalyserEnabled, sentSolutions, noStudents);
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }
}
