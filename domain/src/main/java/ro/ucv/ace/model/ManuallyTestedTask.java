package ro.ucv.ace.model;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ro.ucv.ace.dto.ResponseMessageDto;
import ro.ucv.ace.model.enums.Language;
import ro.ucv.ace.socket.IJobResult;
import ro.ucv.ace.socket.ISocketManager;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "MANUALLY_TESTED_TASK")
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class ManuallyTestedTask extends Task {

    @Autowired
    @Transient
    private ISocketManager socketManager;

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

    @Override
    public ResponseMessageDto addSolution(Solution solution) {
        getSolutions().add(solution);

        IJobResult result = null;

        ResponseMessageDto compilationMessage = compileSolution(solution, result);
        if (compilationMessage != null) return compilationMessage;

        return new ResponseMessageDto("You successfully uploaded your solution!");
    }




}
