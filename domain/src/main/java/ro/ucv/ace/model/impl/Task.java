package ro.ucv.ace.model.impl;

import ro.ucv.ace.model.enums.Language;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "TASK")
@Inheritance(strategy = InheritanceType.JOINED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    @Basic
    private String name;

    @Column(name = "DESCRIPTION")
    @Basic
    private String description;

    @Column(name = "DEADLINE")
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANGUAGE")
    private Language language;

    @Column(name = "CAN_SUBMIT_SOLUTIONS")
    @Basic
    private boolean canSubmitSolutions;

    @ManyToOne
    @JoinColumn(name = "TOPIC_ID", referencedColumnName = "ID")
    private Topic topic;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Solution> solutions = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isCanSubmitSolutions() {
        return canSubmitSolutions;
    }

    public void setCanSubmitSolutions(boolean canSubmitSolutions) {
        this.canSubmitSolutions = canSubmitSolutions;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }
}
