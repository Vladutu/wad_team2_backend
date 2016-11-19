package ro.ucv.ace.model.impl;

import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.visitor.SubgroupVisitor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "SUBGROUP")
public class Subgroup implements ISubgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, unique = true)
    @Basic
    private String name;

    @OneToMany(mappedBy = "subgroup")
    private List<Student> students = new ArrayList<>();

    public Subgroup() {
    }

    public Subgroup(String name) {
        this.name = name;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public void accept(SubgroupVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void update(String name) {
        this.name = name;
    }
}
