package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.model.impl.User;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.ILoginRepository;

/**
 * Created by Geo on 15.11.2016.
 */
@Repository("loginRepository")
public class LoginRepository implements ILoginRepository {

    @Autowired
    private IJpaRepository<IAuthenticatable, User, Integer> authenticatableRepository;

    @Override
    public IAuthenticatable getByUsername(String username) {
        return authenticatableRepository.findOneWhere(user -> user.getUsername().equals(username));
    }
}
