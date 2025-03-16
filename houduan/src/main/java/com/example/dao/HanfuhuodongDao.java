package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Hanfuhuodong;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HanfuhuodongDao extends BaseMapper<Hanfuhuodong> {

@Select("select * from hanfuhuodong")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
