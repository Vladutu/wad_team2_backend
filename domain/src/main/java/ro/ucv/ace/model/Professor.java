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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Embedded
    private PersonDetails personDetails;

    @Column(name = "POSITION", nullable = false)
    @Basic
    private String position;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}
