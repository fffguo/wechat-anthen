package com.yzy.wechat.serviceopen.mapper;

import com.yzy.wechat.serviceopen.entity.PayWechat;
import com.yzy.wechat.serviceopen.entity.PayWechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayWechatMapper {
    long countByExample(PayWechatExample example);

    int deleteByExample(PayWechatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayWechat record);

    int insertSelective(PayWechat record);

    List<PayWechat> selectByExample(PayWechatExample example);

    PayWechat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PayWechat record, @Param("example") PayWechatExample example);

    int updateByExample(@Param("record") PayWechat record, @Param("example") PayWechatExample example);

    int updateByPrimaryKeySelective(PayWechat record);

    int updateByPrimaryKey(PayWechat record);
}