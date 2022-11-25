package pers.qc233.basemanagesystem.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.qc233.basemanagesystem.Pojo.Record;

@Mapper
public interface RecordDao extends BaseMapper<Record> {
}
