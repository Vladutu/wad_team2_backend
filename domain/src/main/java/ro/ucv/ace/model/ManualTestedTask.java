package ro.ucv.ace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 28.10.2016.
 */
@Entity
@Table(name = "MANUAL_TESTED_TASK")
public class ManualTestedTask extends AbstractTask {

    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 5000, nullable = false)
    private String description;

    @Column(name = "LANGUAGE", length = 30, nullable = false)
    private String language;

    public ManualTestedTask() {
    }

    public ManualTestedTask(String name, String description, String language) {
        this.name = name;
        this.description = description;
        this.language = language;
    }

    @Override
    public String toString() {
        return "ManualTestedTask{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public void setName(String updatedName) {
        this.name = updatedName;
    }
}
