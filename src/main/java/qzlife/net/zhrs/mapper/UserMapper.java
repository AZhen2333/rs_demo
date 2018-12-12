package qzlife.net.zhrs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import qzlife.net.zhrs.entity.po.UserPo;
import qzlife.net.zhrs.mappers.MyMapper;

@Mapper
@Component
public interface UserMapper extends MyMapper<UserPo> {
}
