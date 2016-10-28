package ro.ucv.ace.model;

import javax.persistence.*;

/**
 * Created by Geo on 28.10.2016.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTask implements Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
