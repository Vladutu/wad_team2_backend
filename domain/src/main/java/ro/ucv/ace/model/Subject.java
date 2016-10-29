package ro.ucv.ace.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 29.10.2016.
 */
@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @Basic
    @Column(name = "CREDITS", nullable = false)
    private Integer credits;

    @ManyToMany(mappedBy = "subjects")
    private List<AbstractTask> tasks = new ArrayList<>();

    public Subject(int id, String name, Integer credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id + "," +
                "name='" + name + '\'' +
                ", credits=" + credits +
                '}';
    }
}
