package ro.ucv.ace.socket;

/**
 * Created by ctotolin on 19-Nov-16.
 */
public class JobResult {
    private String result;
    private String error;

    public JobResult() {}

    public boolean isError(){
        return error != null;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}
