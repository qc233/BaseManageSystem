package pers.qc233.basemanagesystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;
import pers.qc233.basemanagesystem.Service.UserService;
import pers.qc233.basemanagesystem.Util.BaseTools;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 处理登录请求 <br>
     * 判空由按钮事件处理
     * @param username 用户名
     * @param password 密码
     * @return 结果信息
     */

    public Result login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.login(user);
    }

    /**
     * 处理注册请求 <br>
     * 可判断密码长度、是否重名<br>
     * 密码采用原始密码+16位随机字符串经由SHA256加密得到
     * @param username 用户名
     * @param password 密码
     * @return 结果信息
     */

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
