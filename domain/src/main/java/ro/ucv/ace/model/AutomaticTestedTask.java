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

    @Column(name = "INPUT_FILE", length = 10000, nullable = false)
    private String inputFile;

    @Column(name = "OUTPUT_FILE", length = 10000, nullable = false)
    private String outputFile;

    public AutomaticTestedTask() {
    }

    public AutomaticTestedTask(String name, String description, String language, String inputFile, String outputFile) {
        super(name, description, language);
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }


    @Override
    public void setName(String updatedName) {
        super.setName(updatedName);
    }

    @Override
    public String toString() {
        return "AutomaticTestedTask{" +
                "inputFile='" + inputFile + '\'' +
                ", outputFile='" + outputFile + '\'' +
                "} " + super.toString();
    }
}
