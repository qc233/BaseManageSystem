package pers.qc233.basemanagesystem.Pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Record {
    int id;
    String username;
    Timestamp time;
    int score;
}
