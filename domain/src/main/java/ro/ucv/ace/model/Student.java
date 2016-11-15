package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "STUDENT")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Embedded
    private PersonDetails personDetails;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "SUBGROUP_ID", referencedColumnName = "ID")
    private Subgroup subgroup;

    @OneToMany(mappedBy = "student")
    private List<Solution> solutions = new ArrayList<>();
}
