package ro.ucv.ace.socket.impl;

import ro.ucv.ace.socket.IJobResult;

/**
 * Created by Geo on 30.11.2016.
 */
public class PlagiarismJobResult extends JobResult implements IJobResult {

    private PlagiarismResult[] plagiarismResults;

    public PlagiarismResult[] getPlagiarismResults() {
        return plagiarismResults;
    }

    public void setPlagiarismResults(PlagiarismResult[] plagiarismResults) {
        this.plagiarismResults = plagiarismResults;
    }
}
