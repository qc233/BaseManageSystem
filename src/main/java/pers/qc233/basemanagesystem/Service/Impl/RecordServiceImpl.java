package pers.qc233.basemanagesystem.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qc233.basemanagesystem.DAO.RecordDao;
import pers.qc233.basemanagesystem.Pojo.Record;
import pers.qc233.basemanagesystem.Pojo.Result;
import pers.qc233.basemanagesystem.Service.RecordService;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public Result insert(Record record) {
        Result result = new Result();

        record.setTime(new Timestamp(new Date().getTime()));
        final int insert = recordDao.insert(record);

        result.setDate(insert);
        result.setMsg("OK");
        result.setCode(200);

        return result;
    }

    @Override
    public Result selectPage(int page, int size) {
        IPage iPage = new Page(page, size);
        Result result = new Result();

        LambdaQueryWrapper<Record> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Record::getScore);
        IPage list = recordDao.selectPage(iPage, lqw);

        result.setCode(200);
        result.setDate(list.getRecords());
        result.setMsg("OK");

        return result;
    }
}
