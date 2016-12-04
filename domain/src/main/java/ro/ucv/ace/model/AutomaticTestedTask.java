package ro.ucv.ace.model;

import ro.ucv.ace.dto.ResponseMessageDto;
import ro.ucv.ace.model.enums.Language;
import ro.ucv.ace.socket.IJob;
import ro.ucv.ace.socket.IJobResult;
import ro.ucv.ace.socket.impl.CompilationJob;
import ro.ucv.ace.socket.impl.TestJob;
import ro.ucv.ace.socket.impl.TestJobResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "AUTOMATIC_TESTED_TASK")

public class AutomaticTestedTask extends Task {

    @Column(name = "TEST_FILES_PATH")
    private String testFilesPath;

    public AutomaticTestedTask() {
    }

    public AutomaticTestedTask(Topic topic, List<Subgroup> subgroups, String name, LocalDate deadline,
                               String description, Language language, PlagiarismAnalyser plagiarismAnalyser) {
        setTopic(topic);
        setSubgroups(subgroups);
        setName(name);
        setDeadline(deadline);
        setDescription(description);
        setLanguage(language);
        setPlagiarismAnalyser(plagiarismAnalyser);
    }

    public String getTestFilesPath() {
        return testFilesPath;
    }

    public void setTestFilesPath(String testFilesPath) {
        this.testFilesPath = testFilesPath;
    }

    @Override
    public boolean hasTestsEnabled() {
        return true;
    }

    @Override
    public boolean hasPlagiarismAnalyserEnabled() {
        return getPlagiarismAnalyser().isEnabled();
    }


    @Override
    public ResponseMessageDto addSolution(Solution solution) {
        getSolutions().add(solution);

        IJob compilationJob = new CompilationJob(solution.getDirectoryPath(), getLanguage());
        IJobResult result = null;

        //Compile and check for compilation errors
        ResponseMessageDto resultMessage = compileSolution(solution, result);
        if (resultMessage != null) {
            return resultMessage;
        }

        //Test the solution
        IJob testJob = new TestJob(solution.getDirectoryPath(), getLanguage());
        result = sendJob(testJob, result);

        if (checkForError(result)) {
            return new ResponseMessageDto(result.getResult());
        }

        TestJobResult testJobResult = (TestJobResult) result;
        solution.setMark(testJobResult.getPassedTests() / testJobResult.getTotalTests() * 100);

        //Send successful notification to student
        return new ResponseMessageDto(result.getResult());
    }

    private IJobResult sendJob(IJob job, IJobResult result) {
        try {
            result = socketManager.sendJob(job).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean checkForError(IJobResult result) {
        if (result.getError()) {
            //Send error notification to student
            return true;
        }

        if (result.getInternalError()) {
            //Send error notification to professor
            return true;
        }
        return false;
    }


}
