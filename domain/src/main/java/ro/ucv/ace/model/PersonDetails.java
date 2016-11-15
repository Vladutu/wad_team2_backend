package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 15.11.2016.
 */
@Embeddable
@Getter
@Setter
public class PersonDetails {

    @Column(name = "FIRST_NAME", nullable = false)
    @Basic
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @Basic
    private String lastName;

    @Column(name = "SSN", nullable = false, unique = true)
    @Basic
    private String ssn;

    @Column(name = "GENDER", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
