package ro.ucv.ace.service;

import ro.ucv.ace.model.IAuthenticatable;

/**
 * Created by Geo on 15.11.2016.
 */
public interface ILoginService {

    IAuthenticatable getByUsername(String username);
}
