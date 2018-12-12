package qzlife.net.zhrs.mapper;/**
 * @Author: Czz
 * @Date: 10/12/2018 5:01 PM
 */

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import qzlife.net.zhrs.entity.po.AppTokenPo;
import qzlife.net.zhrs.mappers.MyMapper;

/**
 * @program: zhrs
 * @description: App端用户token记录mapper
 * @author: Czz
 * @create: 2018-12-10 17:01
 **/
@Mapper
@Component
public interface AppTokenMapper extends MyMapper<AppTokenPo> {
}
