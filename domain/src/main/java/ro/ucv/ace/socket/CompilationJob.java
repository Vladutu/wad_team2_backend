package ro.ucv.ace.socket;

/**
 * Created by ctotolin on 19-Nov-16.
 */
public class CompilationJob extends Job{


    private String path;
    private String language;
    private String type;

    public CompilationJob(String path, String language){
        this.path = path;
        this.language = language;
        this.type = "compile";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getType() {
        return this.type;
    }
}