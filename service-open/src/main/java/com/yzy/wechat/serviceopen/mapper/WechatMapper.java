package com.yzy.wechat.serviceopen.mapper;

import com.yzy.wechat.serviceopen.entity.Wechat;
import com.yzy.wechat.serviceopen.entity.WechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}