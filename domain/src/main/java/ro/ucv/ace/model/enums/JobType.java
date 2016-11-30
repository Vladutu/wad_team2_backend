package ro.ucv.ace.model.enums;

/**
 * Created by Geo on 30.11.2016.
 */
public enum JobType {
    COMPILE("compile"), TEST("test"), PLAGIARISM("plagiarism");

    private String name;

    JobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
