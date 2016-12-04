package ro.ucv.ace.model;

import javax.persistence.*;

/**
 * Created by Geo on 04.12.2016.
 */
@Entity
@Table(name = "PLAGIARISM_RESULT")
public class PlagiarismResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

    private String url;

    private float similarityPercent;

    @ManyToOne
    @JoinColumn(name = "SOLUTION_ID", referencedColumnName = "ID")
    private Solution solution;

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getSimilarityPercent() {
        return similarityPercent;
    }

    public void setSimilarityPercent(float similarityPercent) {
        this.similarityPercent = similarityPercent;
    }
}
