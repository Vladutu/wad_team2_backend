package ro.ucv.ace.model;

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
}
