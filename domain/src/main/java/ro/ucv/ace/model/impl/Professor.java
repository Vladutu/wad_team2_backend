package ro.ucv.ace.model.impl;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "PROFESSOR")
public class Professor extends User {

    @Column(name = "POSITION", nullable = false)
    @Basic
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
