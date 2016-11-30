package ro.ucv.ace.socket.impl;

import ro.ucv.ace.model.enums.JobType;
import ro.ucv.ace.socket.IJob;

/**
 * Created by Geo on 30.11.2016.
 */
public class PlagiarismJob extends Job implements IJob {

    private String[] paths;

    public PlagiarismJob(String[] paths) {
        super(JobType.PLAGIARISM);
        this.paths = paths;
    }
}
