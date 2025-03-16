package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Kepuxinxi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface KepuxinxiDao extends BaseMapper<Kepuxinxi> {

@Select("select * from kepuxinxi")
List<Map<String, Object>> daochuexcel();

//youxxianxci

@Select("SELECT distinct(kepuleixing) as aa,count(id) as bb FROM kepuxinxi group by kepuleixing order by id")List<Map<String, Object>> kepuxinxi_tj_kepuleixing();//tonxgji1

}
