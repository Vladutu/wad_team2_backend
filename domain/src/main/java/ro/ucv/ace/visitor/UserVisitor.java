package ro.ucv.ace.visitor;

import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.model.impl.User;

/**
 * Created by tzapt on 11/16/2016.
 */
public class UserVisitor {

    private UserDto userDto;

    public void visit(User user) {
        userDto = new UserDto(user.getId(), user.getUsername(), user.getRole(), "", user.getPersonDetails().getFirstName(),
                user.getPersonDetails().getLastName(), user.getPersonDetails().getGender().toString(),
                user.getPersonDetails().getSsn(), user.getAccount().getEmail());
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
