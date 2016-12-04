package ro.ucv.ace.model;

import ro.ucv.ace.model.enums.Language;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "MANUALLY_TESTED_TASK")
public class ManuallyTestedTask extends Task {

    public ManuallyTestedTask() {
    }

    public ManuallyTestedTask(Topic topic, List<Subgroup> subgroups, String name, LocalDate deadline, String description, Language language, PlagiarismAnalyser plagiarismAnalyser) {
        setTopic(topic);
        setSubgroups(subgroups);
        setName(name);
        setDeadline(deadline);
        setDescription(description);
        setLanguage(language);
        setPlagiarismAnalyser(plagiarismAnalyser);
    }

    @Override
    public boolean hasTestsEnabled() {
        return false;
    }

    @Override
    public boolean hasPlagiarismAnalyserEnabled() {
        return getPlagiarismAnalyser().isEnabled();
    }
}
