package ro.ucv.ace.dto.studentGrade;

/**
 * Created by tzapt on 12/10/2016.
 */
public class ESStudentGradeDto {

    private String mark;

    public ESStudentGradeDto() {
    }

    public ESStudentGradeDto(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
