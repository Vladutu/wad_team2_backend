package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "SECRETARY")
@Getter
@Setter
public class Secretary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Embedded
    private PersonDetails personDetails;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}
