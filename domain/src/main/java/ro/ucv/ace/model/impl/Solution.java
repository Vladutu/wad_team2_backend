package ro.ucv.ace.model.impl;

import javax.persistence.*;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "SOLUTION")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    private int mark;

    private String directoryPath;

    private boolean canUpload;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", referencedColumnName = "ID")
    private Task task;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isCanUpload() {
        return canUpload;
    }

    public void setCanUpload(boolean canUpload) {
        this.canUpload = canUpload;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
