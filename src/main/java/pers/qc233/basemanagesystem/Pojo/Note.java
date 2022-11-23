package pers.qc233.basemanagesystem.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("note")
public class Note {
    int id;
    int ownerId;
    Timestamp createTime;
    Timestamp lastChangeTime;
    boolean isPrivate;
    String context;
}
