package pers.qc233.basemanagesystem.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("record")
public class Record {
    int id;
    String username;
    Timestamp time;
    int score;
}
