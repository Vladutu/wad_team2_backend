package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.IUser;
import ro.ucv.ace.model.impl.User;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.IUserRepository;

/**
 * Created by tzapt on 11/16/2016.
 */
@Repository("userRepository")
public class UserRepository implements IUserRepository {

    @Autowired
    private IJpaRepository<IUser, User, Integer> innerUserRepository;

    @Override
    public IUser getByUsername(String username) {
        return innerUserRepository.findOneWhere(user -> user.getAccount().getUsername().equals(username));
    }
}
