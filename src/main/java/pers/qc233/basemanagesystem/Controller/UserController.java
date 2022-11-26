package pers.qc233.basemanagesystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Pojo.User;
import pers.qc233.basemanagesystem.Service.UserService;

@Component
public class UserController {

    @Autowired
    UserService userService;

    public User getUserById(User user){
        return (User) userService.getUserById(user).getDate();
    }

    public void update(User user){
        userService.update(user);
    }
}
