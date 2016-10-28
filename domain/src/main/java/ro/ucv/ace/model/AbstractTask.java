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

    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 5000, nullable = false)
    private String description;

    @Column(name = "LANGUAGE", length = 30, nullable = false)
    private String language;

    public AbstractTask() {
    }

    public AbstractTask(String name, String description, String language) {
        this.name = name;
        this.description = description;
        this.language = language;
    }

    @Override
    public void setName(String updatedName) {
        name = updatedName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
