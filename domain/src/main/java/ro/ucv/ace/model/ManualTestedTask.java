package ro.ucv.ace.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 28.10.2016.
 */
@Entity
@Table(name = "MANUAL_TESTED_TASK")
public class ManualTestedTask extends AbstractTask {

    public ManualTestedTask() {
    }

    public ManualTestedTask(String name, String description, String language) {
        super(name, description, language);
    }


    @Override
    public void setName(String updatedName) {
        super.setName(updatedName);
    }

    @Override
    public void addSubject(Subject subject) {
        super.addSubject(subject);
    }

    @Override
    public String toString() {
        return "ManualTestedTask{} " + super.toString();
    }
}
