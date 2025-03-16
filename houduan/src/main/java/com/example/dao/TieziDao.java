package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Tiezi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TieziDao extends BaseMapper<Tiezi> {

@Select("select * from tiezi")
List<Map<String, Object>> daochuexcel();

//tonxgji1
}
