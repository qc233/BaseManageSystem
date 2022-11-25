package pers.qc233.basemanagesystem.Service;

import pers.qc233.basemanagesystem.Pojo.Record;
import pers.qc233.basemanagesystem.Pojo.Result;

public interface RecordService {
    /**
     * 插入数据
     * @param record Record对象
     * @return 结果信息
     */
    public Result insert(Record record);

    /**
     * 读取某一页数据
     * @param page 页码
     * @param size 页面容量
     * @return 结果数据
     */
    public Result selectPage(int page, int size);
}
