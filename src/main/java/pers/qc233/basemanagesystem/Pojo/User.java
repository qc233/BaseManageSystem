package pers.qc233.basemanagesystem.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user")
public class User {
    int id;
    String username;
    String salt;
    String password;
    Timestamp registerTime;
    Timestamp lastLoginTime;
}
