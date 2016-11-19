package ro.ucv.ace.model.impl;

import ro.ucv.ace.model.IProfessor;
import ro.ucv.ace.model.enums.UserRole;
import ro.ucv.ace.visitor.ProfessorVisitor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "PROFESSOR")
public class Professor extends User implements IProfessor {

    @Column(name = "POSITION", nullable = false)
    @Basic
    private String position;

    public Professor() {

    }

    public Professor(String firstName, String gender, String lastName, String position, String ssn, String email, String username, String password) {
        PersonDetails personDetails = new PersonDetails(firstName, lastName, ssn, gender);
        Account account = new Account(email, username, password, UserRole.PROFESSOR);
        this.setAccount(account);
        this.setPersonDetails(personDetails);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void accept(ProfessorVisitor visitor) {
        visitor.visit(this);
    }

}
