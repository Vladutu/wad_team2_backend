package ro.ucv.ace.model;

import ro.ucv.ace.visitor.SolutionVisitor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "MARK")
    @Basic
    private double mark;

    @Column(name = "DIRECTORY_PATH", unique = true)
    @Basic
    private String directoryPath;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", referencedColumnName = "ID")
    private Task task;

    @OneToMany(mappedBy = "solution", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlagiarismResult> plagiarismResults = new ArrayList<>();

    public Solution() {
    }

    public Solution(Student student, Task task, String stringPath) {
        setStudent(student);
        setTask(task);
        setDirectoryPath(stringPath);
    }

    public List<PlagiarismResult> getPlagiarismResults() {
        return plagiarismResults;
    }

    public void setPlagiarismResults(List<PlagiarismResult> plagiarismResults) {
        this.plagiarismResults = plagiarismResults;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
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

    public void accept(SolutionVisitor solutionVisitor) {
        solutionVisitor.visit(this);
    }
}
