package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.HanfuNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface HanfuNewsDao extends BaseMapper<HanfuNews>{

    @Select("select * from HanfuNews")
    List<Map<String, Object>> daochuexcel();
}
