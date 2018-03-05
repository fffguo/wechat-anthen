package com.yzy.wechat_anthen.mapper;

import com.yzy.wechat_anthen.entity.OpenPlatform;
import com.yzy.wechat_anthen.entity.OpenPlatformExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OpenPlatformMapper {
    long countByExample(OpenPlatformExample example);

    int deleteByExample(OpenPlatformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpenPlatform record);

    int insertSelective(OpenPlatform record);

    List<OpenPlatform> selectByExample(OpenPlatformExample example);

    OpenPlatform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpenPlatform record, @Param("example") OpenPlatformExample example);

    int updateByExample(@Param("record") OpenPlatform record, @Param("example") OpenPlatformExample example);

    int updateByPrimaryKeySelective(OpenPlatform record);

    int updateByPrimaryKey(OpenPlatform record);


    /** 通过appid 和 status 查询 一条 开放平台记录 */
    OpenPlatform findOneByAppidAndStatus(Map<String,Object> map);
}