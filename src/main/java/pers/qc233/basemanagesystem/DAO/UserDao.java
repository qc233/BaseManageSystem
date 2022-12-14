package pers.qc233.basemanagesystem.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.qc233.basemanagesystem.Pojo.User;

/**
 * 由MyBatisPlus完全代理SQL接口
 */

@Mapper
public interface UserDao extends BaseMapper<User> {
}
