package ro.ucv.ace.socket.impl;

import ro.ucv.ace.socket.IJobResult;

/**
 * Created by ctotolin on 25-Nov-16.
 */
public class CompilationJobResult implements IJobResult {

    private String error;
    private boolean successful;
    private String response;

    public CompilationJobResult(String response, boolean successful, String error) {
        this.response = response;
        this.successful = successful;
        this.error = error;
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    @Override
    public String getError() {
        return this.error;
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
