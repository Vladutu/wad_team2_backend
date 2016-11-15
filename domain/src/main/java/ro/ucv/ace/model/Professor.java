package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "PROFESSOR")
@Getter
@Setter
public class Professor extends User {

    @Column(name = "POSITION", nullable = false)
    @Basic
    private String position;
}
