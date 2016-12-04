package ro.ucv.ace.dto.solution;

/**
 * Created by tzapt on 12/4/2016.
 */
public class SolutionDto {

    private int id;

    private int mark;

    private String directoryPath;

    public SolutionDto() {
    }

    public SolutionDto(int id, int mark, String directoryPath) {
        this.id = id;
        this.mark = mark;
        this.directoryPath = directoryPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
