package ro.ucv.ace.repository;

import ro.ucv.ace.model.IUser;

/**
 * Created by tzapt on 11/16/2016.
 */
public interface IUserRepository {
    IUser getByUsername(String username);

    boolean usernameExists(String username);
}
