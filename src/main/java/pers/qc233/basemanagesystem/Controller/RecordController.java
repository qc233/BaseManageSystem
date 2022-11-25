package pers.qc233.basemanagesystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.qc233.basemanagesystem.Pojo.Record;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Service.RecordService;

@Component
public class RecordController {

    @Autowired
    RecordService recordService;

    public Result insertRecord(Record record){
        return recordService.insert(record);
    }

    public Result selectRecordPage(int page, int size){
        return recordService.selectPage(page,size);
    }
}
