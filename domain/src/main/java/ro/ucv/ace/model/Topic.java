package ro.ucv.ace.model;

import ro.ucv.ace.visitor.TopicVisitor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    @Basic
    private String name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "ID")
    private Professor professor;

    public Topic() {

    }

    public Topic(String name, int professorId) {
        this.name = name;
        this.professor = new Professor(professorId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void accept(TopicVisitor visitor) {
        visitor.visit(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
}
