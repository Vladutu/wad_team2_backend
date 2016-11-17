package ro.ucv.ace.model.impl;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.model.IUser;
import ro.ucv.ace.visitor.UserVisitor;

import javax.persistence.*;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class User implements IAuthenticatable, IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Account account;

    @Embedded
    private PersonDetails personDetails;

    @Autowired
    @Transient
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean passwordMatches(String password) {
        return passwordEncoder.matches(password, this.account.getPassword());
    }

    @Override
    public void accept(UserVisitor userVisitor) {
        userVisitor.visit(this);
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getRole() {
        return account.getRole().toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }
}
