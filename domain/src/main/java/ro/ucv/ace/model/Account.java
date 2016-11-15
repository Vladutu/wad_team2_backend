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
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    @Basic
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @Basic
    private String password;

    @Column(name = "EMAIL", nullable = false)
    @Basic
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole type;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Notification> notifications = new ArrayList<>();
}
