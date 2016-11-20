package ro.ucv.ace.model;

import ro.ucv.ace.visitor.StudentVisitor;

/**
 * Created by tzapt on 11/20/2016.
 */
public interface IStudent {

    void accept(StudentVisitor visitor);

    void update(String firstName, String lastName, String ssn, String email, String gender, ISubgroup subgroup);
}
