package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Geo on 15.11.2016.
 */
@Entity
@Table(name = "SECRETARY")
@Getter
@Setter
public class Secretary extends User {

}
