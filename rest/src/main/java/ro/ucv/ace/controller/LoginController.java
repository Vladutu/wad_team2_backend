package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.dto.user.UserLoginDto;
import ro.ucv.ace.exception.EntityBindingException;
import ro.ucv.ace.service.ILoginService;
import ro.ucv.ace.socket.IJob;
import ro.ucv.ace.socket.impl.CompilationJob;
import ro.ucv.ace.socket.impl.SocketManager;

import javax.validation.Valid;

/**
 * Created by tzapt on 11/16/2016.
 */

@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private SocketManager socketManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postLogin(@Valid @RequestBody UserLoginDto userLogin, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            throw new EntityBindingException(bindResult.getFieldErrors());
        }
        UserDto userDto = loginService.authenticateUser(userLogin);

        IJob job = new CompilationJob("/test_compilation", "JAVA");
        socketManager.sendJob(job);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
