package pers.qc233.basemanagesystem.Service;

import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Pojo.User;

public interface UserService {
    public Result login(User user);
    public Result register(User user);
    public Result getUserById(User user);
    public Result update(User user);
}
