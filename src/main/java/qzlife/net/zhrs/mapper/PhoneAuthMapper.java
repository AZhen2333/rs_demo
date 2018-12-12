package qzlife.net.zhrs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import qzlife.net.zhrs.entity.po.PhoneAuth;
import qzlife.net.zhrs.mappers.MyMapper;

@Mapper
@Component
public interface PhoneAuthMapper extends MyMapper<PhoneAuth> {
}
