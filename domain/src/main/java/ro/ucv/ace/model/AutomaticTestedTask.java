package ro.ucv.ace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 28.10.2016.
 */
@Entity
@Table(name = "AUTOMATIC_TESTED_CLASS")
public class AutomaticTestedTask extends AbstractTask {

    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 5000, nullable = false)
    private String description;

    @Column(name = "LANGUAGE", length = 30, nullable = false)
    private String language;

    @Column(name = "INPUT_FILE", length = 10000, nullable = false)
    private String inputFile;

    @Column(name = "OUTPUT_FILE", length = 10000, nullable = false)
    private String outputFile;

    public AutomaticTestedTask() {
    }

    public AutomaticTestedTask(String name, String description, String language, String inputFile, String outputFile) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public String toString() {
        return "AutomaticTestedTask{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", inputFile='" + inputFile + '\'' +
                ", outputFile='" + outputFile + '\'' +
                '}';
    }

    @Override
    public void setName(String updatedName) {
        this.name = updatedName;
    }
}
