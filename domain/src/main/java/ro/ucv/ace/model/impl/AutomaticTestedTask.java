package ro.ucv.ace.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "AUTOMATIC_TESTED_TASK")
public class AutomaticTestedTask extends Task {

    @Column(name = "INPUT_FILE_PATH")
    private String inputFilePath;

    @Column(name = "OUTPUT_FILE_PATH")
    private String outputFilePath;

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}
