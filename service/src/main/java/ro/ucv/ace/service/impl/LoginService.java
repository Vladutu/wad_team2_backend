package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.repository.ILoginRepository;
import ro.ucv.ace.service.ILoginService;

/**
 * Created by Geo on 15.11.2016.
 */
@Service("loginService")
public class LoginService implements ILoginService {

    @Autowired
    private ILoginRepository loginRepository;

    @Override
    public IAuthenticatable getByUsername(String username) {
        return loginRepository.getByUsername(username);
    }
}
