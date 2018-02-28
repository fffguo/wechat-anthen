package com.yzy.wechat.serviceopen.mapper;

import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.entity.WechatExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WechatMapper {
    long countByExample(WechatExample example);

    int deleteByExample(WechatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wechat record);

    int insertSelective(Wechat record);

    List<Wechat> selectByExample(WechatExample example);

    Wechat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByExample(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);

    /** 根据状态和wechat类型，获取wechat详情 */
    Wechat findOneByStatusAndTypeAndAppid(Map<String,Object> map);
}