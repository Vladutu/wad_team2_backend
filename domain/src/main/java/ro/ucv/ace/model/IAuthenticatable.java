package ro.ucv.ace.model;

/**
 * Created by Geo on 15.11.2016.
 */
public interface IAuthenticatable {

    String getUsername();

    String getPassword();

    String getRole();
}
