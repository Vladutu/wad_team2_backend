package ro.ucv.ace.socket.impl;

import ro.ucv.ace.socket.IJobResult;

/**
 * Created by Geo on 30.11.2016.
 */
public class PlagiarismJobResult extends JobResult implements IJobResult {

    private PlagiarismMatch[] plagiarismMatches;

    public PlagiarismMatch[] getPlagiarismMatches() {
        return plagiarismMatches;
    }

    public void setPlagiarismMatches(PlagiarismMatch[] plagiarismMatches) {
        this.plagiarismMatches = plagiarismMatches;
    }
}
