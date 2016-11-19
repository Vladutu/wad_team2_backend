package ro.ucv.ace.model;

import ro.ucv.ace.visitor.ProfessorVisitor;

/**
 * Created by tzapt on 11/19/2016.
 */
public interface IProfessor {

    void accept(ProfessorVisitor visitor);

}
