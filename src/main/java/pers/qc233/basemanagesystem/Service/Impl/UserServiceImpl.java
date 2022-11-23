package pers.qc233.basemanagesystem.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qc233.basemanagesystem.DAO.UserDao;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;
import pers.qc233.basemanagesystem.Service.UserService;
import pers.qc233.basemanagesystem.Util.BaseTools;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result login(User user) {
        Result result = new Result();
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.eq(User::getUsername,user.getUsername());
        final User selectUser = (User) userDao.selectOne(lqw);

        if (selectUser == null){
            result.setCode(-1);
            result.setMsg("用户名不存在");
        }else {
            String sha = BaseTools.getSHA(user.getPassword()+selectUser.getSalt());
            if (sha.equals(selectUser.getPassword())){
                result.setCode(200);
                result.setMsg("登陆成功");
                selectUser.setLastLoginTime(new Timestamp(new Date().getTime()));
                userDao.updateById(selectUser);
            }else {
                result.setCode(403);
                result.setMsg("密码错误");
            }
        }
        result.setDate(null);
        return result;
    }

    @Override
    public Result register(User user) {
        Result result = new Result();

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        final Long count = userDao.selectCount(lqw);
        if(count == 0){
            final int insert = userDao.insert(user);
            result.setCode(200);
            result.setMsg("注册成功");
            result.setDate(insert);
        }else {
            result.setCode(409);
            result.setMsg("用户名已存在");
            result.setDate(null);
        }
        return result;
    }
}
