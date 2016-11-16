package ro.ucv.ace.repository;

import ro.ucv.ace.model.IAuthenticatable;

/**
 * Created by Geo on 15.11.2016.
 */
public interface ILoginRepository {

    IAuthenticatable getByUsername(String username);
}
