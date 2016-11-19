package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.dto.UserLoginDto;
import ro.ucv.ace.exception.InvalidPasswordException;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.model.IUser;
import ro.ucv.ace.repository.ILoginRepository;
import ro.ucv.ace.repository.IUserRepository;
import ro.ucv.ace.service.ILoginService;
import ro.ucv.ace.socket.CompilationJob;
import ro.ucv.ace.socket.JobResult;
import ro.ucv.ace.socket.SocketManager;
import ro.ucv.ace.visitor.UserVisitor;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Geo on 15.11.2016.
 */
@Service("loginService")
@Transactional(rollbackFor = Exception.class)
public class LoginService implements ILoginService {

    @Autowired
    private ILoginRepository loginRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private SocketManager manager;

    @Override
    public IAuthenticatable getByUsername(String username) {
        return loginRepository.getByUsername(username);
    }

    @Override
    public UserDto authenticateUser(UserLoginDto userLogin) {
        System.out.println("Inside auth user");
        Future<JobResult> response = manager.sendJob(new CompilationJob("asdf", "asdf"));
        try {
            System.out.println("Final response is: " + response.get().getResult());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        IUser user = userRepository.getByUsername(userLogin.getUsername().toLowerCase());


        if (!user.passwordMatches(userLogin.getPassword())) {
            throw new InvalidPasswordException("Invalid user password!");
        }

        UserVisitor userVisitor = new UserVisitor();

        user.accept(userVisitor);

        UserDto userDto = userVisitor.getUserDto();

        String token = userLogin.getUsername() + ":" + userLogin.getPassword();
        byte[] bytes = Base64.getEncoder().encode(token.getBytes());
        userDto.setAuthorization("Basic " + new String(bytes, Charset.forName("UTF-8")));

        return userDto;
    }
}
