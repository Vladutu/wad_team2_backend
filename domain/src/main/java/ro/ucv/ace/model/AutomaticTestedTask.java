package ro.ucv.ace.model;

import ro.ucv.ace.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

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
        setCanSubmitSolutions(true);
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
}
