package pers.qc233.basemanagesystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;
import pers.qc233.basemanagesystem.Service.Impl.UserServiceImpl;
import pers.qc233.basemanagesystem.Service.UserService;
import pers.qc233.basemanagesystem.Util.BaseTools;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class LoginController {
    @Autowired
    private UserService userService;

    public Result login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.login(user);
    }

    public Result register(String username, String password){
        Result result = new Result();
        User user = new User();

        if (password.length() < 8){
            result.setCode(401);
            result.setMsg("密码长度太短");
            result.setDate(null);
        }else {
            String salt = BaseTools.getRandomStr(16);
            user.setUsername(username);
            user.setSalt(salt);
            user.setPassword(BaseTools.getSHA(password + salt));
            user.setRegisterTime(new Timestamp(new Date().getTime()));
            result = userService.register(user);
        }
        return result;
    }

}
