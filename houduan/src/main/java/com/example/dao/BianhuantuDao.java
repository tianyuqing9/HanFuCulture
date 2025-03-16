package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Bianhuantu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BianhuantuDao extends BaseMapper<Bianhuantu> {

@Select("select * from bianhuantu")
List<Map<String, Object>> daochuexcel();

//tonxgji1
}
