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
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "SUBGROUP_ID", referencedColumnName = "ID")
    private Subgroup subgroup;

    @OneToMany(mappedBy = "student")
    private List<Solution> solutions = new ArrayList<>();
}
