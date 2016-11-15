package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.model.enums.UserRole;

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
    @Column(name = "USER_ROLE")
    private UserRole role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @JoinColumn(name = "ID")
    @OneToOne
    @MapsId
    private User user;
}
