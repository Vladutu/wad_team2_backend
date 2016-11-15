package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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

    @OneToMany(mappedBy = "task")
    private List<Solution> solutions = new ArrayList<>();
}
