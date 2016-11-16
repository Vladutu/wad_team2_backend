package ro.ucv.ace.model;

import ro.ucv.ace.visitor.UserVisitor;

/**
 * Created by tzapt on 11/16/2016.
 */
public interface IUser {

    String getPassword();

    void accept(UserVisitor userVisitor);
}
